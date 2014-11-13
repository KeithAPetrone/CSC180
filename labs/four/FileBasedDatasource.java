import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class FileBasedDatasource
{
	private HashMap<java.lang.Integer, Auction> map = null;
	
	public FileBasedDatasource(HashMap<java.lang.Integer, Auction> map)
	{
		this.map = map;
	}
	
	public void save()
	{
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter("auction.dat"));
				for (int i = 0; i <= map.values().toArray().length; i++)
				{
					if (map.values().toArray()[i] != null && ((Auction) map.values().toArray()[i]).isDead())
					{
						out.write("$$tombstone$$");
						out.println();
						i++;
					}	
					else if (map.values().toArray()[i] != null)
					{
						out.write(map.values().toArray()[i].toString());
						out.println();
					}
				}
		} catch (IOException | ArrayIndexOutOfBoundsException e) {
			//Do nothing
		}
		out.close();
	}
	
	public InMemoryAuctionService load()
	{
		TableRowAuctionConverter converter = new TableRowAuctionConverter();
		InMemoryAuctionService service = new InMemoryAuctionService();
		BufferedReader br = null;
		Date date = new Date();
		try {
			br = new BufferedReader(new FileReader("auction.dat"));
			String line = "";
			while ((line = br.readLine()) != null)
			{
				if (!line.contains("$$tombstone$$"))
				{
					line = line.replace("\t", "-");
					String[] s = line.split("--");
					s[0] = s[0].replace("-", "");
					date = 	new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(s[5]);
					s[2] = s[2].replace("$", "");
					String owner = s[4];
					if (owner.equals("null"))
					{
						owner = null;
					}
					service.create(new Auction(Integer.parseInt(s[0]), s[1], Integer.parseInt(s[2]), "Made by system", date, Integer.parseInt(s[3]), owner));
				}
			}
			br.close();
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return service;
	}
}
