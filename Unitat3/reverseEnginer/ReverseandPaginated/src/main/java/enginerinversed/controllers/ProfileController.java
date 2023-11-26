package enginerinversed.controllers;

import enginerinversed.utils.HibernateUtil;
import java.util.Collections;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;
import enginerinversed.entities.*;

public class ProfileController {

    public ProfileController() {
    }

        public List<Profile> getAllProfiles() {
        Session laSesion = HibernateUtil.getSessionFactory().openSession();
        try {
            laSesion.getTransaction().begin();
            Query<Profile> query = laSesion.createQuery("FROM Profile", Profile.class);
            List<Profile> profileList = query.getResultList();
            laSesion.getTransaction().commit();
            return profileList;
        } catch (Exception e) {
            if (laSesion.getTransaction() != null) {
                laSesion.getTransaction().rollback();
            }
            System.out.println("Error getting all profiles. " + e.getMessage());
            return Collections.emptyList();
        } finally {
            laSesion.close();
        }
    }
        public void insertProfile(Profile profile) {
            Session laSesion = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = laSesion.getTransaction();
            try {
                transaction.begin();
                laSesion.save(profile);
                transaction.commit();
                System.out.println("Profile inserted correctly");
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                System.out.println("Error inserting profile. " + e.getMessage());
            } finally {
                if (laSesion != null && laSesion.isOpen()) {
                    laSesion.close();
                }
            }
        }

    public void deleteProfile(Long profileId) {
        Session laSesion = HibernateUtil.getSessionFactory().openSession();
        try {
            laSesion.getTransaction().begin();
            Profile profile = laSesion.get(Profile.class, profileId);
            if (profile != null) {
                laSesion.delete(profile);
                laSesion.getTransaction().commit();
                System.out.println("Profile deleted correctly");
            } else {
                System.out.println("Profile with ID " + profileId + " not found");
            }
        } catch (Exception e) {
            if (laSesion.getTransaction() != null) {
                laSesion.getTransaction().rollback();
            }
            System.out.println("Error deleting profile. " + e.getMessage());
        } finally {
            laSesion.close();
        }
    }

    public void updateProfile(Long profileId, String newBio) {
        Session laSesion = HibernateUtil.getSessionFactory().openSession();
        try {
            laSesion.getTransaction().begin();
            Profile profile = laSesion.get(Profile.class, profileId);
            if (profile != null) {
                profile.setBio(newBio);
                laSesion.saveOrUpdate(profile);
                laSesion.getTransaction().commit();
                System.out.println("Profile updated correctly");
            } else {
                System.out.println("Profile with ID " + profileId + " not found");
            }
        } catch (Exception e) {
            if (laSesion.getTransaction() != null) {
                laSesion.getTransaction().rollback();
            }
            System.out.println("Error updating profile. " + e.getMessage());
        } finally {
            laSesion.close();
        }
    }
}
