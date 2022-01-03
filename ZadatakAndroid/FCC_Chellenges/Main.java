package FCC_Chellenges;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<FCC_Chellenges.Contact> contacts;
    private static Scanner scanner;
    private static int id=0;

    public static void main(String[] args) {

        contacts =new ArrayList<>();
        System.out.println("WelCome to our Application");
        showInitialOptions();

    }
    private static void showInitialOptions(){
        System.out.println("Please select one: "+"\n\t1.Manage contacts"+
                "\n\t2.Messages"+
                "\n\t3.Quit");
         scanner =new Scanner(System.in);
         int choice =scanner.nextInt();
         switch (choice){
             case 1:
                 manageContacts();
                 break;
             case 2:
                 manageMessages();
                 break;
             default:
                 System.out.println("\n\t\t\t\tTHANK'S FOR USING OUR APPLICATION");
                 break;
         }
    }


    private static void manageContacts(){
        System.out.println("Please select one:"+
                "\n\t1.Show all contacts"+
                "\n\t2.Add a new contact"+
                "\n\t3.Search for a contact"+
                "\n\t4.Delete a contact"
                +"\n\t5.Go back to the previous menu");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                showAllContacts();
                break;
            case 2:
                addNewContact();
                break;
            case 3:
                searchForContact();
                break;
            case 4:
                deleteContact();
                break;
            default:
                showInitialOptions();
                break;
        }
    }

    private static void showAllContacts() {
        if (contacts.isEmpty()){
            System.out.println("There ia not any contact yet");
            manageMessages();
        }else {
            for (Contact c : contacts) {
                c.getDetails();
                System.out.println("\n**********");
            }
            manageContacts();
        }
    }


    private static void addNewContact() {
        System.out.println("Adding a contact");
        System.out.println("Please enter contact's name: ");
        String name = scanner.next();
        System.out.println("Please enter contact's number: ");
        String number = scanner.next();
        System.out.println("Please enter contact's email: ");
        String email = scanner.next();

        if(name.equals("") || number.equals("") || email.equals("") ){
            System.out.println("Please enter all the information.");
            addNewContact();
        }else {
            Contact contact = new Contact(name, number, email);
            contacts.add(contact);
            System.out.println("Contact added successfully \n");
        }
        manageContacts();

    }


    private static void searchForContact(){
        System.out.println("Please enter contact's name: ");
        String name=scanner.next();
        if (name.equals("")){
            searchForContact();
        }else{
            boolean isExist =false;
            for (Contact c : contacts){
                if (c.getName().equals(name)){
                    isExist=true;
                    c.getDetails();
                    System.out.println("\n\t\t\t\t**********");
                }
            }
            if (!isExist){
                System.out.println("There is no contact with name: "+name);
            }

            manageContacts();
        }
    }


    private static void deleteContact(){
        System.out.println("Please enter contact's name: ");
        String name= scanner.next();
        if (name.equals("")){
            deleteContact();
        }else {
            boolean isExist= false;
            for (Contact c:contacts){
                if (c.getName().equals(name)){
                    isExist=true;
                    contacts.remove(c);
                    System.out.println("Deleted successfully");
                    break;
                }
            }
            if (!isExist){
                System.out.println("There is not a such contact exist.");
            }
            manageContacts();
        }
    }



    private static void manageMessages(){
        System.out.println("Please select one:"+
                "\n\t1.See the list of all messages"+
                "\n\t2.Send a new message"+
                "\n\t3.Go back to previous menu"
                );
        int choice= scanner.nextInt();
        switch (choice){
            case 1:
                seeAllMessages();
                break;
            case 2:
                sendNewMessage();
                break;
            default:
                showInitialOptions();
                break;
        }
    }


    private static void seeAllMessages(){
        ArrayList<Message> allMessages =new ArrayList<>();
        for (Contact c: contacts){
            allMessages.addAll(c.getMessages());
        }

        if (allMessages.size()>0){
            for (Message m: allMessages){
                m.getDetail();
                System.out.println("\n**********");
            }
        }else {
            System.out.println("You don't have any message yet");
        }
        manageMessages();
    }

    private static void sendNewMessage(){
        System.out.println("Whom you're going to send text: ");
        String name= scanner.next();
        if (name.equals("")){
            System.out.println("please enter a valid name");
            sendNewMessage();
        } else {
            boolean doesExist = false;
            for (Contact c : contacts){
                if (c.getName().equals(name)){
                    doesExist = true;}
            }
            if (doesExist){
                    System.out.println("Enter you message: ");
                    String messagetext=scanner.next();
                    if (messagetext.equals("")){
                        System.out.println("Please enter something to send");
                        sendNewMessage();
                    }else {
                        id++;
                        Message newMessage= new Message(messagetext,name,id);
                        for (Contact co : contacts){
                            if (co.getName().equals(name)){
                                ArrayList<Message> allmessages =co.getMessages();
                                allmessages.add(newMessage);
                                co.setMessages(allmessages);
                                System.out.println("Sended succcessfully");
                                manageMessages();
                            }
                        }
                    }

                }else {
                    System.out.println("There is not such contact in your contact list");
                    manageMessages();
                }
            }
    }


}
