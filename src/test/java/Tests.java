import Service.Parser;
import model.AviaTicket;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static jdk.nashorn.internal.objects.Global.println;

public class Tests {


    @Test
    public void checkWorkAddDeleteUpdate() throws Exception {
        Parser parser = new Parser();
        List<AviaTicket> listAct;


        parser.parse("add  Point1,111,Fname Fname.Sname.,01.10.2011");
        parser.parse("add  Point1,111,Fname Fname.Sname.,01.10.2011");
        listAct = parser.getAviaTicketList();
        Assert.assertEquals(listAct.size(),2);


        AviaTicket aviaTicketUPD = listAct.get(0);
        parser.parse("update "+aviaTicketUPD.getId()+",Point2,222,Fname2 Fname2.Sname2.,02.12.2022");
        HashMap<Integer, AviaTicket> aviaTicketHashMap = new HashMap<>();
        for (AviaTicket aviaTicketOutList : listAct) {
            aviaTicketHashMap.put(aviaTicketOutList.getId(), aviaTicketOutList);
        }
        Assert.assertEquals(aviaTicketHashMap.get(aviaTicketUPD.getId()).toString(), aviaTicketUPD.getId()+",Point2,222,Fname2 Fname2.Sname2.,02.12.2022");


        parser.parse("delete "+aviaTicketUPD.getId());
        listAct = parser.getAviaTicketList();
        AviaTicket aviaTicketF = listAct.get(0);
        Assert.assertEquals(aviaTicketF.toString(),  aviaTicketF.getId()+",Point1,111,Fname Fname.Sname.,01.10.2011");

        parser.parse("delete "+aviaTicketF.getId());
        listAct = parser.getAviaTicketList();
        Assert.assertEquals(listAct.size(),0);
    }
}
