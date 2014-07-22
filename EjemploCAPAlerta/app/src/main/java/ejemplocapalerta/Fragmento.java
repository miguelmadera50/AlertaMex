package ejemplocapalerta;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class Fragmento extends DialogFragment{

    /**
     * Permite utilizar solo una insatancia del objeto
     * de forma estatica, no es necesario instanciar
     * un fragmento adicional para generar otra ventana de dialogo.
     *
     * @param titulo String que determina el mensaje
     *               simple dentro de la alerta*/
    static Fragmento newInstance(String titulo){

        Fragmento fr = new Fragmento();
        Bundle args = new Bundle();

        /*Guarda el mensaje en el objeto Bundle*/
        args.putString("Titulo", titulo);

        fr.setArguments(args);

        return fr;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        /*Obtiene el String del objeto Bundle*/
        String titulo = getArguments().getString("Titulo");

        /*Genera una alerta*/
        return new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.ic_launcher)
                .setTitle(titulo)
                .setMessage("Evento Catastrofico en tu area!")

                .setNeutralButton("Reportar Evento",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {

                            }
                        })
                .setNeutralButton("Reportar Mensaje",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                            }
                        }
                ).create();
    }
}