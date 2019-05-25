import com.routeone.interview.Receipt;
import com.routeone.interview.StoreRegister;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Tests {

    @Test
    public void Nothing() throws FileNotFoundException {
        StoreRegister storeRegister = new StoreRegister();
        storeRegister.loadInventory(new File("RouteOneSampleInventory.csv"));
        Receipt receipt = storeRegister.checkoutOrder(storeRegister.getFile());
        List<String> empty = new ArrayList<String>();

        assertEquals("$0", receipt.getFormattedTotal());
        assertEquals(empty, receipt.getOrderedItems());
    }

    @Test
    public void Something() throws FileNotFoundException {
        StoreRegister storeRegister = new StoreRegister();
        storeRegister.loadInventory(new File("RouteOneSampleInventory.csv"));
        List<String> input = new ArrayList<String>();
        input.add("1");
        input.add("1");
        input.add("1");
        Receipt receipt = storeRegister.checkoutOrder(input);

        List<String> item1 = new ArrayList<String>();
        List<String> item2 = new ArrayList<String>();
        List<String> item3 = new ArrayList<String>();
        List<String> items = new ArrayList<String>();
        item1.add("PC800," + " 9.99," + " RAM");
        item2.add("PC800," + " 9.99," + " RAM");
        item3.add("PC800," + " 9.99," + " RAM");
        items.add(item1.toString());
        items.add(item2.toString());
        items.add(item3.toString());

        assertEquals("$29.97", receipt.getFormattedTotal());
        assertEquals(items, receipt.getOrderedItems());
    }
}
