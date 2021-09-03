package com.pauldelaney.vendingmachine;

import com.pauldelaney.vendingmachine.controller.VendingMachineController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author pauldelaney
 */
public class App {

    public static void main(String[] args) {

        /*
        UserIO myIo = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIo);

        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoFileImpl();

        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
        VendingMachineService myService = new VendingMachineServiceImpl(auditDao, myDao);

        VendingMachineController controller = new VendingMachineController(myView, myService);
        controller.run();
         */
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller
                = ctx.getBean("controller", VendingMachineController.class);
        controller.run();

    }

}
