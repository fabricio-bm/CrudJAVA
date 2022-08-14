import org.hibernate.Session;
import br.com.CrudPOO.util.HibernateUtil;

public class TestarConexao {

	public static void main(String[] args) {
		Session sessao = HibernateUtil.getFabrica().openSession();
		sessao.close();
		HibernateUtil.getFabrica().close();
	}

}
















