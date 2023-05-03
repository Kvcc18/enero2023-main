package com.mayab.desarrollo.persistence;
import com.mayab.desarrollo.entities.Usuario;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;
import com.mayab.desarrollo.main.HibernateUtil;
import java.util.List;

public class UserDAO implements IUserDAO{
    @Override
    public List<Usuario> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Usuario> list = session.createQuery("from Usuario").list();
        session.close();
        return list;
    }

    @Override
    public int createUser(Usuario usuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        int id = (int) session.save(usuario);
        session.getTransaction().commit();
        session.close();
        return id;
    }

    @Override
    public boolean deleteUser(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Usuario u = session.get(Usuario.class, id);
        session.delete(u);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Usuario findById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Usuario u = session.get(Usuario.class, id);
        session.close();
        return u;
    }

    @Override
    public Usuario updatePass(Usuario usuario, String NContra) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Usuario toUpdate = session.get(Usuario.class, usuario.getId());
        toUpdate.setPassword(NContra);
        session.getTransaction().commit();
        session.close();
        return toUpdate;
    }
    @Override
    public Usuario findByName(String nombre){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query qry = session.createQuery("FROM Usuario WHERE nombre=:name")
                    .setParameter("name", nombre);
            Usuario user = (Usuario) qry.getSingleResult();
            user.toString();
            session.close();
            return user;
        }catch (NoResultException e){
            return null;
        }
    }
    @Override
    public Usuario findByEmail(String email){
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query qry = session.createQuery("FROM Usuario WHERE email=:email")
                    .setParameter("email", email);
            Usuario user = (Usuario) qry.getSingleResult();
            user.toString();
            session.close();
            return user;
        }catch (NoResultException e){
            return null;
        }
    }
}
