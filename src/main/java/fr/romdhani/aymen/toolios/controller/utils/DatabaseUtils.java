package fr.romdhani.aymen.toolios.controller.utils;

import fr.romdhani.aymen.toolios.core.orm.*;
import fr.romdhani.aymen.toolios.core.wrapper.Function;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import static fr.romdhani.aymen.toolios.utils.HibernateUtil.getSession;

/**
 * This class used to populate the database in the first time
 */
public class DatabaseUtils {
    private static class DatabaseInitializerHolder {
        public static final DatabaseUtils instance = new DatabaseUtils();
    }

    public static DatabaseUtils getInstance() {
        return DatabaseInitializerHolder.instance;
    }

    private DatabaseUtils() {
    }

    /**
     * Populate the user roles table
     */
    public void populateRoles() {
        System.out.println("INFO- Start to populate user role table...");
        Session session = getSession();
        Transaction tr = session.getTransaction();
        tr.begin();
        // Create user role
        List<UserRoles> userRolesList = createUseRole(session, UserRoles.UserRole.USER.getRole());
        if (userRolesList.size() == 0) {
            UserRoles userRole = new UserRoles();
            userRole.setName(UserRoles.UserRole.USER.getRole());
            session.save(userRole);
        }
        // Create admin role
        List<UserRoles> adminRolesList = createUseRole(session, UserRoles.UserRole.ADMIN.getRole());

        if (adminRolesList.size() == 0) {
            UserRoles adminRole = new UserRoles();
            adminRole.setName(UserRoles.UserRole.ADMIN.getRole());
            session.save(adminRole);
        }
        // Create editor role
        List<UserRoles> editorRolesList = createUseRole(session, UserRoles.UserRole.EDITOR.getRole());
        if (editorRolesList.size() == 0) {
            UserRoles editorRole = new UserRoles();
            editorRole.setName(UserRoles.UserRole.EDITOR.getRole());
            session.save(editorRole);
        }
        // Create manager role
        List<UserRoles> managerRolesList = createUseRole(session, UserRoles.UserRole.MANAGER.getRole());
        if (managerRolesList.size() == 0) {
            UserRoles managerRole = new UserRoles();
            managerRole.setName(UserRoles.UserRole.MANAGER.getRole());
            session.save(managerRole);
        }
        tr.commit();
    }

    /**
     * @param session  the used session
     * @param userRole the user role
     * @return
     */
    private List<UserRoles> createUseRole(Session session, String userRole) {
        return (List<UserRoles>) session.createCriteria(UserRoles.class).add(Restrictions.eq("name", userRole)).list();
    }

    /**
     * Populate the user function table
     */
    public void populateFunction() {
        System.out.println("INFO- Start to populate user function table...");
        Session session = getSession();
        Transaction tr = session.getTransaction();
        tr.begin();
        // Create user function : software engineer
        List<UserFunction> softwareEngineerList = createUserFunction(session, Function.SOFTWARE_ENGINEER.getName());
        if (softwareEngineerList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(Function.SOFTWARE_ENGINEER.getName());
            session.save(userFunction);
        }
        // Create user function : electronics engineer
        List<UserFunction> electronicsEngineerList = createUserFunction(session, Function.ELECTRONICS_ENGINEER.getName());
        if (electronicsEngineerList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(Function.ELECTRONICS_ENGINEER.getName());
            session.save(userFunction);
        }
        // Create user function : electronics engineer
        List<UserFunction> administrativeManagerList = createUserFunction(session, Function.ADMINISTRATIVE_MANAGER.getName());
        if (administrativeManagerList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(Function.ADMINISTRATIVE_MANAGER.getName());
            session.save(userFunction);
        }
        // Create user function : administrative assistant
        List<UserFunction> administrativeAssistantList = createUserFunction(session, Function.ADMINISTRATIVE_ASSISTANT.getName());
        if (administrativeAssistantList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(Function.ADMINISTRATIVE_ASSISTANT.getName());
            session.save(userFunction);
        }
        // Create user function : logistics analyst
        List<UserFunction> logisticsAnalystList = createUserFunction(session, Function.LOGISTICS_ANALYST.getName());
        if (logisticsAnalystList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(Function.LOGISTICS_ANALYST.getName());
            session.save(userFunction);
        }
        // Create user function :NMR_PRODUCT_MANAGER
        List<UserFunction> nmrProductManagerList = createUserFunction(session, Function.NMR_PRODUCT_MANAGER.getName());
        if (nmrProductManagerList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(Function.NMR_PRODUCT_MANAGER.getName());
            session.save(userFunction);
        }
        // Create user function : logistics analyst
        List<UserFunction> ctoList = createUserFunction(session, Function.CTO.getName());
        if (ctoList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(Function.CTO.getName());
            session.save(userFunction);
        }
        // Create user function : RESEARCH_ENGINEER
        List<UserFunction> researchEngineerList = createUserFunction(session, Function.RESEARCH_ENGINEER.getName());
        if (researchEngineerList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(Function.RESEARCH_ENGINEER.getName());
            session.save(userFunction);
        }
        // Create user function : MRI_PRODUCT_MANAGER
        List<UserFunction> mriProductManagerList = createUserFunction(session, Function.MRI_PRODUCT_MANAGER.getName());
        if (mriProductManagerList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(Function.MRI_PRODUCT_MANAGER.getName());
            session.save(userFunction);
        }
        // Create user function : SCIENTIFIC_MARKETING
        List<UserFunction> scientificMarketingList = createUserFunction(session, Function.SCIENTIFIC_MARKETING.getName());
        if (scientificMarketingList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(Function.SCIENTIFIC_MARKETING.getName());
            session.save(userFunction);
        }
        // Create user function : SERVICE_ENGINEER
        List<UserFunction> serviceEngineerList = createUserFunction(session, Function.SERVICE_ENGINEER.getName());
        if (serviceEngineerList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(Function.SERVICE_ENGINEER.getName());
            session.save(userFunction);
        }
        // Create user function : CUSTOMER_INSTALLATIONS
        List<UserFunction> customerInstallationList = createUserFunction(session, Function.CUSTOMER_INSTALLATIONS.getName());
        if (customerInstallationList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(Function.CUSTOMER_INSTALLATIONS.getName());
            session.save(userFunction);
        }

        // Create user function : ELECTRONICS_TECHNICIAN
        List<UserFunction> electronicsTechnicianList = createUserFunction(session, Function.ELECTRONICS_TECHNICIAN.getName());
        if (electronicsTechnicianList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(Function.ELECTRONICS_TECHNICIAN.getName());
            session.save(userFunction);
        }
        // Create user function : SENIOR_SALES_MANAGER
        List<UserFunction> seniorSalesList = createUserFunction(session, Function.SENIOR_SALES_MANAGER.getName());
        if (seniorSalesList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(Function.SENIOR_SALES_MANAGER.getName());
            session.save(userFunction);
        }
        // Create user function : SALES_MANAGER
        List<UserFunction> salesManagerList = createUserFunction(session, Function.SALES_MANAGER.getName());
        if (salesManagerList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(Function.SALES_MANAGER.getName());
            session.save(userFunction);
        }
        tr.commit();
    }

    /**
     * @param session      the used session
     * @param functionName the user function
     * @return
     */
    private List<UserFunction> createUserFunction(Session session, String functionName) {
        return (List<UserFunction>) session.createCriteria(UserFunction.class).add(Restrictions.eq("name", functionName)).list();
    }

    /**
     * Populate groups
     */
    public synchronized void populateGroups() {
        System.out.println("INFO- Start to populate groups  table ...");
        Transaction tr = getSession().getTransaction();
        tr.begin();
        Company company = new Company();
        company.setName("RS2D");
        getSession().save(company);
        tr.commit();
    }

    /**
     * Populate groups
     */
    public synchronized void populateGroup() {

        System.out.println("INFO- Start to populate user group table ...");
        Transaction tr = getSession().getTransaction();
        tr.begin();
        Company company = new Company();
        company.setName("RS2D");
        getSession().save(company);

        UserGroup userGroup = new UserGroup();
        userGroup.setName("Software");
        userGroup.setCompany(company);
        getSession().save(userGroup);
        tr.commit();
    }

    public List<UserAccount> getUsers() {
        return (List<UserAccount>) getSession().createQuery("from UserAccount").list();
    }
}
