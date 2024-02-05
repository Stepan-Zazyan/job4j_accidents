package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentHibernate implements AccidentRepository {
    private final SessionFactory sf;

    public Accident create(Accident accident) {
        try (Session session = sf.openSession()) {
            session.save(accident);
            return accident;
        }
    }

    public List<Accident> getAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Accident", Accident.class)
                    .list();
        }
    }

    @Override
    public Optional<Accident> findById(Integer id) {
        Session session = sf.openSession();
        Accident accident = new Accident();
        try {
            Query<Accident> query = session
                    .createQuery("from Accident where id = :fId ", Accident.class);
            query.setParameter("fId", id);
            accident = query.uniqueResult();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Optional.ofNullable(accident);
    }

    @Override
    public boolean update(Accident accident) {
        Session session = sf.openSession();

        boolean rsl = false;
        try {
            session.beginTransaction();
            session.createQuery(
                            "UPDATE Accident SET name = :fname , text = :ftext, "
                                    + "address = :faddress WHERE id = :fId")
                    .setParameter("fId", accident.getId())
                    .setParameter("fname", accident.getName())
                    .setParameter("ftext", accident.getText())
                    .setParameter("faddress", accident.getAddress())
                    .executeUpdate();
            session.getTransaction().commit();
            rsl = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rsl;
    }

}
