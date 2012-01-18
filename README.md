## Verwendung des Google EventBus

*Um in folgenden Projekten mit der neu eingesetzten Eventhandling Methode zu arbeiten hier ein kleiner Guide zum verwenden des EventBus.*

Die Verwendung des EventBus soll das Erzeugen von Tastatur und Maus Events näher an die zu modifizierenden Objekte binden. Die zuvor genutzten AWT EventListener werden hierdurch uberflüssig.

Möchte man nun (als Beispiel) die Position eines Shapes im Framework per Tastatur steuern so muss man lediglich im Shape das Interface InputHandler implementieren und kann nun die benötigten Handlingfunktionen in der Klasse implementieren. Wie zum Beispiel **public void moveShape(KeyEvent e){...}**. Wichtig ist nur dass die __@Subscribe__ Notation über der beliebig benannten Handling Methode gesetzt wird.

In der Framework Klasse muss noch der InputHandler gebunden werden. Hierfür steht die Funktion **registerInputEventHandler()** in der selbenn Klasse.

### Beispiel:

```java

public class MeinFenster extends Framework{
	public MeinFenster(int width,int height)
	{
		super(width,height);
	}

	public void initGraph()
	{
		Shape s = new MeinShape();
		this.registerInputEventHandler(s);
		this.add(s);
	}
}

public class MeinShape extends Shape implements InputHandler{
	@Subscribe
	public void moveMyShape(KeyEvent e)
	{
		System.out.println(e);
	}

	@Override
	public void render(Matrix transformation, Camera camera, Renderer renderer) {...}
}

```

Wichtig ist außerdem dass diese Methode bei keyRelease und keyPress aufgerufen wird, zum unterscheiden dieser Events empfiehlt sich die Event Methode __getID()__;

### Beispiel:

```java

public class MeinShape extends Shape implements InputHandler{
	@Subscribe
	public void moveMyShape(KeyEvent e)
	{
		if(e.getID() == KEY_RELEASED)
		{
			System.out.println("Taste Losgelassen");
		}
		else
		{
			System.out.println("Taste Gedrückt");
		}
	}

	@Override
	public void render(Matrix transformation, Camera camera, Renderer renderer) {...}
}

```

Die folgenden Events stehen im Framework zur Verfügung:

* KeyEvent (Pressed,Released)
* MouseEvent (Clicked,Pressed,Released,Entered,Exited,Dragged,Moved)
* MouseWheelEvent (WheelMoved)

*Um Die EventTypen (KeyEvent,MouseEvent und MouseWheelEvent) zu unterscheiden müssen für jeden Eventtyp eigene Handlingmethoden mit dem spezifischen Übergabeparameter erstellt werden.*

Um InputHandler vom Framework zu lösen steht die Methode **unregisterInputEventHandler()** zur Verfügung. Die Bindung an den EventBus ist jederzeit möglich.
