package Graphics;

public class CharacterView implements UpdateInterface
{
	private Character character;
	private IceView iceView;
	public void setCharacter(Character c) {
		character = c;
	}
	public IceView getIceView() {
		return iceView;
	}
	public Character getCharacter() {
		return character;
	}
	public CharacterView(IceView i, Character c) {
		character = c;
		iceView = i;
	}
	@Override
	public void update()
	{
		
	}

}
