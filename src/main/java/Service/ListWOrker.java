package Service;

import model.AviaTicket;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class ListWOrker {

    private List<AviaTicket> aviaTicketList = new ArrayList<AviaTicket>();
    private WriterInFile writerInFile = new WriterInFile();

    public void addTicket(AviaTicket aviaTicket) throws Exception {

        if(aviaTicket.getId() == 0){
            Random random = new Random();
            if (aviaTicketList.size() == Integer.MAX_VALUE){
                throw new Exception("kek");
            }
            int countTryForTestTask = 10;
            for (int i = 0; i < countTryForTestTask; i++) {
                Integer index = random.nextInt(Integer.MAX_VALUE);
                if (!aviaTicketList.stream().map(AviaTicket::getId).collect(Collectors.toList()).contains(index)){
                    aviaTicket.setId(index);
                    break;
                    }
                }
            }
        if(aviaTicketList.stream().map(AviaTicket::getId).collect(Collectors.toList()).contains(aviaTicket.getId())){
            throw new Exception("id not unic");
        }
        this.aviaTicketList.add(aviaTicket);
        writerInFile.writeAviaTicket(aviaTicket);
    }

    public void deleteTicket(int idTicket) throws Exception {
        HashMap<Integer, AviaTicket> aviaTicketHashMap = getAviaTicketHashMap();
        if (!aviaTicketHashMap.keySet().contains(idTicket)) {
            throw new Exception("not find by id ticket ");
        }
        aviaTicketList.remove(aviaTicketHashMap.get(idTicket));
    }

    public void updateTicket(AviaTicket aviaTicket) throws Exception {
        HashMap<Integer, AviaTicket> aviaTicketHashMap = getAviaTicketHashMap();
        if (!aviaTicketHashMap.keySet().contains(aviaTicket.getId())) {
            throw new Exception("id not found");
        }
        aviaTicketList.remove(aviaTicketHashMap.get(aviaTicket.getId()));
        aviaTicketList.add(aviaTicket);
        writerInFile.writeAviaTicket(aviaTicket);
    }

    public void show(int idTicket) throws Exception {
        HashMap<Integer, AviaTicket> aviaTicketHashMap = getAviaTicketHashMap();
        if (!aviaTicketHashMap.keySet().contains(idTicket)) {
            throw new Exception("id not find");
        }
        writerInFile.writeAviaTicket(aviaTicketHashMap.get(idTicket));
        System.out.println(aviaTicketHashMap.get(idTicket));
    }

    public void show(){
        for (AviaTicket aviaTicket : aviaTicketList){
            System.out.println(aviaTicket);
            writerInFile.writeAviaTicket(aviaTicket);
        }
    }

    public List<AviaTicket> getAviaTicketList(){
        return aviaTicketList;
    }

    @NotNull
    private HashMap<Integer, AviaTicket> getAviaTicketHashMap() {
        HashMap<Integer, AviaTicket> aviaTicketHashMap = new HashMap<>();
        for (AviaTicket aviaTicketOutList : aviaTicketList) {
            aviaTicketHashMap.put(aviaTicketOutList.getId(), aviaTicketOutList);
        }
        return aviaTicketHashMap;
    }
}
