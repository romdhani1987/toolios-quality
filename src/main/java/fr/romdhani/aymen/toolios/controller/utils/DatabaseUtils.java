package fr.romdhani.aymen.toolios.controller.utils;

import fr.romdhani.aymen.toolios.core.orm.*;
import fr.romdhani.aymen.toolios.core.wrapper.CompanyEnum;
import fr.romdhani.aymen.toolios.core.wrapper.FunctionEnum;
import fr.romdhani.aymen.toolios.core.wrapper.GroupEnum;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Optional;

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
        List<UserFunction> softwareEngineerList = createUserFunction(session, FunctionEnum.SOFTWARE_ENGINEER.getName());
        if (softwareEngineerList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(FunctionEnum.SOFTWARE_ENGINEER.getName());
            session.save(userFunction);
        }
        // Create user function : electronics engineer
        List<UserFunction> electronicsEngineerList = createUserFunction(session, FunctionEnum.ELECTRONICS_ENGINEER.getName());
        if (electronicsEngineerList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(FunctionEnum.ELECTRONICS_ENGINEER.getName());
            session.save(userFunction);
        }
        // Create user function : electronics engineer
        List<UserFunction> administrativeManagerList = createUserFunction(session, FunctionEnum.ADMINISTRATIVE_MANAGER.getName());
        if (administrativeManagerList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(FunctionEnum.ADMINISTRATIVE_MANAGER.getName());
            session.save(userFunction);
        }
        // Create user function : administrative assistant
        List<UserFunction> administrativeAssistantList = createUserFunction(session, FunctionEnum.ADMINISTRATIVE_ASSISTANT.getName());
        if (administrativeAssistantList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(FunctionEnum.ADMINISTRATIVE_ASSISTANT.getName());
            session.save(userFunction);
        }
        // Create user function : logistics analyst
        List<UserFunction> logisticsAnalystList = createUserFunction(session, FunctionEnum.LOGISTICS_ANALYST.getName());
        if (logisticsAnalystList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(FunctionEnum.LOGISTICS_ANALYST.getName());
            session.save(userFunction);
        }
        // Create user function :NMR_PRODUCT_MANAGER
        List<UserFunction> nmrProductManagerList = createUserFunction(session, FunctionEnum.NMR_PRODUCT_MANAGER.getName());
        if (nmrProductManagerList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(FunctionEnum.NMR_PRODUCT_MANAGER.getName());
            session.save(userFunction);
        }
        // Create user function : logistics analyst
        List<UserFunction> ctoList = createUserFunction(session, FunctionEnum.CTO.getName());
        if (ctoList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(FunctionEnum.CTO.getName());
            session.save(userFunction);
        }
        // Create user function : RESEARCH_ENGINEER
        List<UserFunction> researchEngineerList = createUserFunction(session, FunctionEnum.RESEARCH_ENGINEER.getName());
        if (researchEngineerList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(FunctionEnum.RESEARCH_ENGINEER.getName());
            session.save(userFunction);
        }
        // Create user function : MRI_PRODUCT_MANAGER
        List<UserFunction> mriProductManagerList = createUserFunction(session, FunctionEnum.MRI_PRODUCT_MANAGER.getName());
        if (mriProductManagerList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(FunctionEnum.MRI_PRODUCT_MANAGER.getName());
            session.save(userFunction);
        }
        // Create user function : SCIENTIFIC_MARKETING
        List<UserFunction> scientificMarketingList = createUserFunction(session, FunctionEnum.SCIENTIFIC_MARKETING.getName());
        if (scientificMarketingList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(FunctionEnum.SCIENTIFIC_MARKETING.getName());
            session.save(userFunction);
        }
        // Create user function : SERVICE_ENGINEER
        List<UserFunction> serviceEngineerList = createUserFunction(session, FunctionEnum.SERVICE_ENGINEER.getName());
        if (serviceEngineerList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(FunctionEnum.SERVICE_ENGINEER.getName());
            session.save(userFunction);
        }
        // Create user function : CUSTOMER_INSTALLATIONS
        List<UserFunction> customerInstallationList = createUserFunction(session, FunctionEnum.CUSTOMER_INSTALLATIONS.getName());
        if (customerInstallationList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(FunctionEnum.CUSTOMER_INSTALLATIONS.getName());
            session.save(userFunction);
        }

        // Create user function : ELECTRONICS_TECHNICIAN
        List<UserFunction> electronicsTechnicianList = createUserFunction(session, FunctionEnum.ELECTRONICS_TECHNICIAN.getName());
        if (electronicsTechnicianList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(FunctionEnum.ELECTRONICS_TECHNICIAN.getName());
            session.save(userFunction);
        }
        // Create user function : SENIOR_SALES_MANAGER
        List<UserFunction> seniorSalesList = createUserFunction(session, FunctionEnum.SENIOR_SALES_MANAGER.getName());
        if (seniorSalesList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(FunctionEnum.SENIOR_SALES_MANAGER.getName());
            session.save(userFunction);
        }
        // Create user function : SALES_MANAGER
        List<UserFunction> salesManagerList = createUserFunction(session, FunctionEnum.SALES_MANAGER.getName());
        if (salesManagerList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(FunctionEnum.SALES_MANAGER.getName());
            session.save(userFunction);
        }
        // Create user function : SALES_MANAGER
        List<UserFunction> financeControllerList = createUserFunction(session, FunctionEnum.FINANCE_CONTROLLER.getName());
        if (financeControllerList.size() == 0) {
            UserFunction userFunction = new UserFunction();
            userFunction.setName(FunctionEnum.FINANCE_CONTROLLER.getName());
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
     * Populate group
     */
    public synchronized void createGroups() {
        System.out.println("INFO- Start to create user groups table...");
        Session session = getSession();
        Transaction tr = session.getTransaction();
        tr.begin();
        List<Company> companyList = (List<Company>) session.createCriteria(Company.class).add(Restrictions.eq("name", "RS2D")).list();
        Company company = companyList.get(0);
        // ADMINISTRATION
        List<UserGroup> userGroupList = createUserGroup(session, GroupEnum.ADMINISTRATION.getName());
        if (userGroupList.size() == 0) {
            UserGroup userGroup = new UserGroup();
            userGroup.setName(GroupEnum.ADMINISTRATION.getName());
            userGroup.setCompany(company);
            session.save(userGroup);
        }
        //MARKETING
        List<UserGroup> marketingList = createUserGroup(session, GroupEnum.MARKETING.getName());
        if (marketingList.size() == 0) {
            UserGroup userGroup = new UserGroup();
            userGroup.setName(GroupEnum.MARKETING.getName());
            userGroup.setCompany(company);
            session.save(userGroup);
        }
        //ELECTRONICS
        List<UserGroup> electronicsList = createUserGroup(session, GroupEnum.ELECTRONICS.getName());
        if (electronicsList.size() == 0) {
            UserGroup userGroup = new UserGroup();
            userGroup.setName(GroupEnum.ELECTRONICS.getName());
            userGroup.setCompany(company);
            session.save(userGroup);
        }
        //SOFTWARE
        List<UserGroup> softwareList = createUserGroup(session, GroupEnum.SOFTWARE.getName());
        if (softwareList.size() == 0) {
            UserGroup userGroup = new UserGroup();
            userGroup.setName(GroupEnum.SOFTWARE.getName());
            userGroup.setCompany(company);
            session.save(userGroup);
        }
        //MRI
        List<UserGroup> mriList = createUserGroup(session, GroupEnum.MRI.getName());
        if (mriList.size() == 0) {
            UserGroup userGroup = new UserGroup();
            userGroup.setName(GroupEnum.MRI.getName());
            userGroup.setCompany(company);
            session.save(userGroup);
        }
        //NMR
        List<UserGroup> nmrList = createUserGroup(session, GroupEnum.NMR.getName());
        if (nmrList.size() == 0) {
            UserGroup userGroup = new UserGroup();
            userGroup.setName(GroupEnum.NMR.getName());
            userGroup.setCompany(company);
            session.save(userGroup);
        }
        //FINANCE
        List<UserGroup> financeList = createUserGroup(session, GroupEnum.FINANCE.getName());
        if (financeList.size() == 0) {
            UserGroup userGroup = new UserGroup();
            userGroup.setName(GroupEnum.FINANCE.getName());
            userGroup.setCompany(company);
            session.save(userGroup);
        }
        //SALES
        List<UserGroup> salesList = createUserGroup(session, GroupEnum.SALES.getName());
        if (salesList.size() == 0) {
            UserGroup userGroup = new UserGroup();
            userGroup.setName(GroupEnum.SALES.getName());
            userGroup.setCompany(company);
            session.save(userGroup);
        }
        //DIRECTION
        List<UserGroup> directionList = createUserGroup(session, GroupEnum.DIRECTION.getName());
        if (directionList.size() == 0) {
            UserGroup userGroup = new UserGroup();
            userGroup.setName(GroupEnum.DIRECTION.getName());
            userGroup.setCompany(company);
            session.save(userGroup);
        }
        //DIRECTION
        List<UserGroup> installList = createUserGroup(session, GroupEnum.INSTALL.getName());
        if (installList.size() == 0) {
            UserGroup userGroup = new UserGroup();
            userGroup.setName(GroupEnum.INSTALL.getName());
            userGroup.setCompany(company);
            session.save(userGroup);
        }
        tr.commit();
    }

    /**
     * @param session
     * @param userGroupName
     * @return
     */
    private List<UserGroup> createUserGroup(Session session, String userGroupName) {
        return (List<UserGroup>) session.createCriteria(UserGroup.class).add(Restrictions.eq("name", userGroupName)).list();
    }

    /**
     * Create a the company
     */
    public synchronized void createCompany() {
        System.out.println("INFO- Start to create company...");
        Session session = getSession();
        Transaction tr = session.getTransaction();
        tr.begin();
        // RS2D
        Optional<Company> companyOpt = getCompany(session, CompanyEnum.RS2D.getName());
        if (!companyOpt.isPresent()) {
            Company company = new Company();
            company.setName("RS2D");
            session.save(company);
        }
        tr.commit();
    }

    /**
     * Gets the list of the user company.
     *
     * @return the list of company in the database
     */
    private Optional<Company> getCompany(Session session, String companyName) {
        return Optional.ofNullable((Company) session.createCriteria(Company.class).add(Restrictions.eq("name", companyName)).uniqueResult());
    }

    /**
     * Gets the list of the users.
     *
     * @return the list of users in the database
     */
    public List<UserAccount> getUsers() {
        return (List<UserAccount>) getSession().createQuery("from UserAccount").list();
    }

    /**
     * Gets the list of the user function.
     *
     * @return the list of user functions in the database
     */
    public List<UserFunction> getUserFunctions() {
        return (List<UserFunction>) getSession().createQuery("from UserFunction").list();
    }

    /**
     * Gets the list of the user groups.
     *
     * @return the list of user groups  in the database
     */
    public List<UserGroup> getUserGroups() {
        return (List<UserGroup>) getSession().createQuery("from UserGroup").list();
    }

    /**
     * Gets the list of the user groups.
     *
     * @return the list of user groups  in the database
     */
    public List<Company> getUserCompany() {
        return (List<Company>) getSession().createQuery("from Company").list();
    }

    /**
     * Gets the list of the user roles.
     *
     * @return the list of user roles in the database
     */
    public List<UserRoles> getUserRoles() {
        return (List<UserRoles>) getSession().createQuery("from UserRoles").list();
    }


}
