package ejemplocapalerta;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Miguel Madera on 7/17/2014.
 */
public class MainActivity extends FragmentActivity {

    /*Declaraciones de Objetos TextView*/
    TextView identifier;
    TextView sender;
    TextView sent;
    TextView status;
    TextView msgType;
    TextView scope;
    TextView category;
    TextView event;
    TextView urgency;
    TextView severity;
    TextView certainty;
    TextView senderName;
    TextView headline;
    TextView description;
    TextView instruction;
    TextView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Inicializaciones*/
        identifier = (TextView) findViewById(R.id.identifier);
        sender = (TextView) findViewById(R.id.sender);
        sent = (TextView) findViewById(R.id.sent);
        status = (TextView) findViewById(R.id.status);
        msgType = (TextView) findViewById(R.id.msgType);
        scope = (TextView) findViewById(R.id.scope);
        category = (TextView) findViewById(R.id.category);
        event = (TextView) findViewById(R.id.event);
        urgency = (TextView) findViewById(R.id.urgency);
        severity = (TextView) findViewById(R.id.severity);
        certainty = (TextView) findViewById(R.id.certainty);
        senderName = (TextView) findViewById(R.id.senderName);
        headline = (TextView) findViewById(R.id.headline);
        description = (TextView) findViewById(R.id.description);
        instruction = (TextView) findViewById(R.id.instruction);
        web = (TextView) findViewById(R.id.web);

        XmlPullParserFactory pullParserFactory;

        /*Objeto XMLPullParser obtiene el archivo XML de los
        * recursos estaticos dentro de /res/raw */
        try {
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();

            InputStream stream = getResources().openRawResource(R.raw.alerta);
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(stream, null);

            parseXML(parser);

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Metodo utiliza el parser del metodo onCreate, extrae los datos
     * relevantes, y los adiciona directamente a los elementos TextView.
     *
     * @param parser Objeto XMLPullParser
     * */
    private void parseXML(XmlPullParser parser)
            throws XmlPullParserException,IOException
    {

        Alerta elementoAlerta = null;
        ArrayList<Alerta> arrAlerta = null;
        int eventType = parser.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT){

            String name = null;

            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    arrAlerta = new ArrayList();
                    break;

                case XmlPullParser.START_TAG:
                    name = parser.getName();

                    if (name.equals("alert")){
                        elementoAlerta = new Alerta();
                    }
                    else if (elementoAlerta != null){
                        if (name.equals("identifier")){
                            elementoAlerta.identifier = parser.nextText();
                        }
                        else if (name.equals("sender")){
                            elementoAlerta.sender = parser.nextText();
                        }
                        else if (name.equals("sent")){
                            elementoAlerta.sent= parser.nextText();
                        }
                        else if (name.equals("status")){
                            elementoAlerta.status = parser.nextText();
                        }
                        else if (name.equals("msgType")){
                            elementoAlerta.msgType= parser.nextText();
                        }
                        else if (name.equals("scope")){
                            elementoAlerta.scope = parser.nextText();
                        }
                        else if (name.equals("category")){
                            elementoAlerta.category = parser.nextText();
                        }
                        else if (name.equals("event")){
                            elementoAlerta.event = parser.nextText();
                        }
                        else if (name.equals("urgency")){
                            elementoAlerta.urgency= parser.nextText();
                        }
                        else if (name.equals("severity")){
                            elementoAlerta.severity = parser.nextText();
                        }
                        else if (name.equals("certainty")){
                            elementoAlerta.certainty= parser.nextText();
                        }
                        else if (name.equals("senderName")){
                            elementoAlerta.senderName = parser.nextText();
                        }
                        else if (name.equals("headline")){
                            elementoAlerta.headline = parser.nextText();
                        }
                        else if (name.equals("description")){
                            elementoAlerta.description= parser.nextText();
                        }
                        else if (name.equals("instruction")){
                            elementoAlerta.instruction = parser.nextText();
                        }
                        else if (name.equals("web")){
                            elementoAlerta.web = parser.nextText();
                        }
                    }
                    break;

                case XmlPullParser.END_TAG:
                    name = parser.getName();

                    if (name.equalsIgnoreCase("alert") && elementoAlerta != null){
                        arrAlerta.add(elementoAlerta);
                    }
            }
            eventType = parser.next();
        }
        imprimirAlerta(arrAlerta);
    }

    /**
     * Imprime los contenidos del ArrayList a la pantalla
     * con el metodo setText()
     *
     * @param alerta ArrayList con datos de archivo XML
     */
    private void imprimirAlerta(ArrayList<Alerta> alerta)
    {

        for(Alerta alertaActual: alerta){
            identifier.setText("Identifier: " + alertaActual.identifier);
            sender.setText("Sender: " + alertaActual.sender);
            status.setText("Status: " + alertaActual.status);
            msgType.setText("Message Type: " + alertaActual.msgType);
            scope.setText("Scope: " + alertaActual.scope);
            category.setText("Category: " + alertaActual.category);
            event.setText("Event: " + alertaActual.event);
            urgency.setText("Urgency: " + alertaActual.urgency);
            severity.setText("Severity: " + alertaActual.severity);
            certainty.setText("Certainty: " + alertaActual.certainty);
            senderName.setText("Category: " + alertaActual.senderName);
            headline.setText("Event: " + alertaActual.headline);
            description.setText("Urgency: " + alertaActual.description);
            instruction.setText("Severity: " + alertaActual.instruction);
            web.setText("Web: " + alertaActual.web);
        }
    }

    /**
     * Genera una alerta en un objeto DialogFragment     *
     */
    public void onClick(View v){
        /*En vez de crear un nevo Fragmento con "new", se invocara
        * el metodo estatico definido dentro de la clase Fragmento*/
        Fragmento dialogFragment = Fragmento.newInstance(
                "Alerta!");

        /*Muestra el DialogFragment*/
        dialogFragment.show(getSupportFragmentManager(), "dialog");
    }
}
