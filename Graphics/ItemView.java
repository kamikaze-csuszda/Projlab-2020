package Graphics;

import Item.Item;

public class ItemView implements UpdateInterface
{
	private IceView iceView;
	private Item item;
	public void setItem(Item i) {
		item = i;
	}
	public Item getItem() {
		return item;
	}
	public void setIceView(IceView i) {
		iceView = i;
	}
	public IceView getIceView() {
		return iceView;
	}
	
	@Override
	public void update()
	{
		// TODO Auto-generated method stub

	}

}
