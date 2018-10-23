package br.com.ohm.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.ohm.conexao.Conexao;
import br.com.ohm.jdbc.JDBCUsuarioDAO;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Servlet implementation class EsqueciSenha
 */
@WebServlet("/EsqueciSenha")
public class EsqueciSenha extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EsqueciSenha() {
		super();
		// TODO Auto-generated constructor stub
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email").toString();
		Conexao conec = new Conexao();
		Connection conexao = conec.abrirConexao();
		JDBCUsuarioDAO jdbcUsuario = new JDBCUsuarioDAO(conexao);
		String login = jdbcUsuario.validaEmail(email);
		Map<String, String> msg = new HashMap<String, String>();
		if (!login.equals("")){ 
			String novaSenha = jdbcUsuario.geraSenha();
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "587");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.starttls.enable", "true");

			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("ohmjogo@gmail.com", "ohmjogo129");
				}
			});
			session.setDebug(true);


			try {
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("ohmjogo@gmail.com"));
				Address[] toUser = InternetAddress 
						.parse(email);  
				message.setRecipients(Message.RecipientType.TO, toUser);
				message.setSubject("Sua senha foi redefinida!!!");
				message.setText("Um nova senha foi requisitada no ohm o Jogo!\n\n"+"Email: "+email+"\nLogin: "+login+"\nNova senha: "+novaSenha);
				Transport.send(message);
				msg.put("msg", "E-mail enviado com sucesso!");
				jdbcUsuario.atualizarSenha(novaSenha, login);
				
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		}else {
			msg.put("msg", "E-mail não encontrado em nossos servidores!");
		}
		conec.fecharConexao();
		String json = new Gson().toJson(msg);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

}
