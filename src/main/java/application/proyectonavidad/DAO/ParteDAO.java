package application.proyectonavidad.DAO;

import application.proyectonavidad.Conexion.Conexion;
import application.proyectonavidad.Model.*;
import application.proyectonavidad.Utils.HibernateUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public class ParteDAO {

    private SessionFactory factory;
    private Session session;

    public ParteDAO() {
        Conexion.conexion();
        factory = Conexion.getFactory();
        session = Conexion.getSession();
    }

    public Alumnos buscarAlumnoByExp(String expediente) {
        Alumnos alumno = null;
        try {
            session.beginTransaction();
            alumno = session.createQuery("from Alumnos where numero_expediente=:numero_expediente", Alumnos.class)
                    .setParameter("numero_expediente", expediente).stream().findFirst().orElse(null);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
        return alumno;
    }

    public boolean insertarParte(Alumnos alumno, Profesores profesor, int puntos, LocalDate fecha, String hora, String descripcion, String sancion) {
        Partes_incidencia parte = new Partes_incidencia(alumno, profesor, puntos, descripcion, fecha.toString(), hora, sancion);
        try {
            session.beginTransaction();
            session.save(parte);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return false;
        } finally {
            session.clear();
        }
    }

    public Partes_incidencia[] buscarPartes() {
        Session session = HibernateUtil.getSessionFactory().openSession(); // Asegúrate de tener un método para obtener la sesión
        Transaction transaction = null;
        List<Partes_incidencia> partesList = null;

        try {
            transaction = session.beginTransaction();
            partesList = session.createQuery("from Partes_incidencia", Partes_incidencia.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return partesList != null ? partesList.toArray(new Partes_incidencia[0]) : new Partes_incidencia[0];
    }

    public Partes_incidencia[] buscarPartesPorId(Alumnos alumno) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Partes_incidencia> partesList = null;

        try {
            transaction = session.beginTransaction();
            partesList = session.createQuery("from Partes_incidencia where id_alum=:id_alum", Partes_incidencia.class)
                    .setParameter("id_alum", alumno).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return partesList != null ? partesList.toArray(new Partes_incidencia[0]) : new Partes_incidencia[0];
    }

}