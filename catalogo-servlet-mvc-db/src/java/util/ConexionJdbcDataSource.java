package util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConexionJdbcDataSource {

    private Connection conn;

    public ConexionJdbcDataSource() {
        conectar();
    }

    private void conectar() {
        try {
            Context contexto = new InitialContext();
            DataSource ds = (DataSource) contexto.lookup("jdbc/javaCurso");
            conn = ds.getConnection();
        } catch (NamingException ex) {
            Logger.getLogger(ConexionJdbcDataSource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJdbcDataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return conn;
    }
}
