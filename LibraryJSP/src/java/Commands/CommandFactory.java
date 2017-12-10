/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

/**
 *
 * @author grahamm
 */
public class CommandFactory {
    
    private volatile static CommandFactory currentInstance;
    
    private CommandFactory(){
        
    }
    
    public static CommandFactory getInstance(){
        
        if(currentInstance == null){
            synchronized(CommandFactory.class)
            {
                if(currentInstance == null){
                    currentInstance = new CommandFactory();
                }
            }
        }
        return currentInstance;
    }
    
    public static Command createCommand(String action){
        // Create a command object to hold what we action we wish to take
        Command command = null;
        
        // Confirm an action variable was supplied
        if (action != null) {
            // Choose which action to carry out
            // Deal with if the user wants to update a customer
            switch (action) {
                // Deal with if the user wants to view all customer information
                case "login":
                    command = new LoginUserCommand();
                    break;
                    
                case "signup":
                    command = new SignUpUserCommand();
                    break;
                    
                case "viewalltitles":
                    command = new ViewAllTitlesCommand();
                    break;
                    
                case "borrowTitle":
                    command = new SelectBorrowedCommand();
                    break;
                    
                case "novelByName":
                    command = new NovelByNameCommand();
                    break;
                    
                case "novelByID":
                    command = new NovelByIDCommand();
                    break;
                    
                case "novelByAuthor":
                    command = new NovelByAuthorCommand();
                    break;
                    
                case "viewborrowedtitle":
                    command = new ViewBorrowedTitlesCommand();
                    break;
                    
                case "logout":
                    command = new LogoutUserCommand();
                    break;
                    
                case "addtitle":
                    command = new AddTitleCommand();
                    break;
                    
                case "updatetitle":
                    command = new UpdateTitleCommand();
                    break;
                    
                case "deletetitle":
                    command = new DeleteTitleCommand();
                    break;
                    
                case "deleteuser":
                    command = new DeleteUserCommand();
                    break;
                default:
                    command = new NoValidActionCommand();
                    break;
            }

        } else {
            // Deal with where there was no action supplied as part of this request
            command = new NoActionSuppliedCommand();
        }
        
        return command;
    }
    
}
