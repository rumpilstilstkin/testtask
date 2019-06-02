package Service;

import com.sun.istack.internal.NotNull;
import model.AviaTicket;

import java.util.List;

public class Parser {
    private ListWOrker listWOrker = new ListWOrker();
    private enum supportCommands {add,delete,show,showall,update};

    public List<AviaTicket> getAviaTicketList(){
        return  listWOrker.getAviaTicketList();
    }

    public void parse(String stringInput) throws Exception {
        String stringInputTrim = stringInput.trim();

        int indexSapce = stringInputTrim.indexOf(" ");
        if (indexSapce == -1 ){
            indexSapce = stringInput.length();
        }

        switch (stringInput.substring(0,indexSapce).toLowerCase()){
            case "add" : {
                AviaTicket aviaTicket = parseAviaTicketData(stringInputTrim.substring(indexSapce));
                listWOrker.addTicket(aviaTicket);
                break;
            }
            case "delete" : {
                int aviaTicket = checkAviaTicketId(stringInputTrim.substring(indexSapce));
                listWOrker.deleteTicket(aviaTicket);
                break;
            }
            case "update" : {
                AviaTicket aviaTicket = parseAviaTicketData(stringInputTrim.substring(indexSapce));
                listWOrker.updateTicket(aviaTicket);
                break;
            }
            case "show" : {
                int aviaTicketId = checkAviaTicketId(stringInputTrim.substring(indexSapce));
                listWOrker.show(aviaTicketId);
                break;
            }
            case "showall" : {
                listWOrker.show();
                break;
            }
            case "exit" : {
                System.exit(-1);
            }
            default:{
                throw new Exception("not parse command");
            }
        }

    }

    public AviaTicket parseAviaTicketData(String aviticketString) throws Exception {

        for(supportCommands command: supportCommands.values()){
            if(aviticketString.toLowerCase().contains(String.format(" %s ", command.toString()))){
                throw new Exception("more command in one string");
            }
        }

        String[] dataAviaTicket =  aviticketString.split(",");

        switch (dataAviaTicket.length){
            case 4 : {
                String tikenName = checAviaTicketName(dataAviaTicket[0]);
                int ticketNumTrip = checkAviaTicketNumTrip(dataAviaTicket[1]);
                String tiketFIO = checAviaTicketFIO(dataAviaTicket[2]);
                String tiketDate = ckeckAviaTicketDate(dataAviaTicket[3]);
                return new AviaTicket(0, tikenName, ticketNumTrip, tiketFIO, tiketDate);
            }
            case 5 : {
                int tiketId = checkAviaTicketId(dataAviaTicket[0]);
                String tikenName = checAviaTicketName(dataAviaTicket[1]);
                int ticketNumTrip = checkAviaTicketNumTrip(dataAviaTicket[2]);
                String tiketFIO = checAviaTicketFIO(dataAviaTicket[3]);
                String tiketDate = ckeckAviaTicketDate(dataAviaTicket[4]);
                return new AviaTicket(tiketId, tikenName, ticketNumTrip, tiketFIO, tiketDate);
            }

            default: {
                throw new Exception("check separator data");
            }

        }
    }


    @NotNull
    private int checkAviaTicketId(String aviTicketId) throws Exception {
        String aviTicketIdStr = "";
        if (!checkTrimStringIsEmply(aviTicketId)) {
            aviTicketIdStr = aviTicketId.trim();
        } else {
            throw new Exception("aviTicketId is emply");
        }
        int aviTicketIdInt = Integer.parseInt(aviTicketIdStr);
        if(aviTicketIdInt> 0) {
            return aviTicketIdInt;
        } else {
            throw new Exception("check aviTicketId number ");
        }
    }

    private String checAviaTicketName(String aviTicketPoint) throws Exception {
        if (!checkTrimStringIsEmply(aviTicketPoint)) {
            return (aviTicketPoint.substring(0, 1).toUpperCase() + aviTicketPoint.substring(1)).trim();
        } else {
            throw new Exception("aviTicketPoint is emply");
        }
    }

    @NotNull
    private String checAviaTicketFIO(String aviTicketFIO) throws Exception {
        if (!checkTrimStringIsEmply(aviTicketFIO)){
            return aviTicketFIO.trim();
        } else {
            throw new Exception("aviTicketFIO is emply");
        }
    }

    @NotNull
    private int checkAviaTicketNumTrip(String aviaTicketNumTrip) throws Exception {
        String aviaTicketNumTripStr  = "";
        if(checkTrimStringIsEmply(aviaTicketNumTrip)) {
            throw new Exception("aviaTicketNumTrip is emply");
        } else {
            aviaTicketNumTripStr = aviaTicketNumTrip.trim();
        }
        int aviaTicketNum = Integer.parseInt(aviaTicketNumTripStr);
        if(aviaTicketNum > 0) {
            return aviaTicketNum;
        } else {
            throw new Exception("aviaTicketNumTrip less zero");
        }
    }

    @NotNull
    private String ckeckAviaTicketDate(String aviTicketDataDate) throws Exception {
        if (checkTrimStringIsEmply(aviTicketDataDate)){
            throw new Exception("aviTicketDataDate is emply");
        }
        //TODO
        return aviTicketDataDate.trim();
    }

    private boolean checkTrimStringIsEmply(String param) {
        return param.trim().isEmpty();
    }
}
