package lab1ui.gr7.compumovil.udea.edu.co.lab1ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Se usa autocompletar para hacer uso en la selección de paises
        AutoCompleteTextView pais = (AutoCompleteTextView) findViewById(R.id.country);
        String[] countries = getResources().getStringArray(R.array.countries_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        pais.setAdapter(adapter);

        //El spinner es usado en conjunto con el res String para dar las opciones multiples
        Spinner pasatiempo = (Spinner) findViewById(R.id.hobbies);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.hobbies_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pasatiempo.setAdapter(adapter2);

    }
    //Este metodo es para el uso del datePicker la vista
    public void onEditSet(View v){

        DialogFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getFragmentManager(), "datePicker");
    }


    //se invoca este metodo para dar accion al boton mostrar
    public void onClick(View view){
        RadioButton mujer = (RadioButton)findViewById(R.id.genderF);
        RadioButton hombre = (RadioButton)findViewById(R.id.genderM);
        String validar="";
        if(mujer.isChecked()){ //esta decision es para saber que casilla esta seleccionada
            validar="Femenino";
        }
        if(hombre.isChecked()){ //esta decision es para saber que casilla esta seleccionada
            validar="Masculiono";
        }

        CheckBox favorito =(CheckBox)findViewById(R.id.Favorito);
        CheckBox noFavorito =(CheckBox)findViewById(R.id.No_Favorito);

        String favor=""; //esta decision es para saber que casilla esta seleccionada
        if(favorito.isChecked()){
            favor="Favorito";
        }//esta decision es para saber que casilla esta seleccionada
        if(noFavorito.isChecked()){
            favor="No favorito";
        }
        //Se declaran las variables a utilizar con el layout y sus identificaciones
        Spinner pasatiempo = (Spinner) findViewById(R.id.hobbies);
        String result = pasatiempo.getSelectedItem().toString();

        TextView texto = (TextView)findViewById(R.id.text);
        EditText nombre = (EditText)findViewById(R.id.name);
        EditText apellido = (EditText)findViewById(R.id.lastName);
        EditText fecha =(EditText)findViewById(R.id.birthDate);
        EditText pais =(EditText)findViewById(R.id.country);
        EditText telefono =(EditText)findViewById(R.id.phone);
        EditText direccion =(EditText)findViewById(R.id.address);
        EditText mail =(EditText)findViewById(R.id.email);

        //se usa para validar los campos a ingresar
        Boolean si=validarCampos();

        if(si==true){//si y solo si es valido los datos//en pantalla muestra todas las casillas
            texto.setText(new StringBuilder().append(nombre.getText()).append("\n").append(apellido.getText()).append("\n").append(validar).append("\n").append(fecha.getText())
                    .append("\n").append(pais.getText()).append("\n").append(telefono.getText()).append("\n").append(direccion.getText()).append("\n").append(mail.getText()).append("\n")
                    .append(result).append("\n").append(favor));
        }



    }

    public boolean validarCampos(){ //se implementa un metodo boolean para validar campos
        //se noto en la implementacion que el solo if or else no funciona tan bien en android para validacion de campos.

        EditText nombre = (EditText)findViewById(R.id.name);
        EditText fecha =(EditText)findViewById(R.id.birthDate);
        EditText pais =(EditText)findViewById(R.id.country);
        EditText telefono =(EditText)findViewById(R.id.phone);
        EditText mail =(EditText)findViewById(R.id.email);;

        String nombre1= nombre.getText().toString();
        if (nombre1.equals("")) {
            nombre.setError("Ha dejado campos vacios");//Muestra en pantalla que el campo esta vacio
            return false;
        }
        String paises=pais.getText().toString();
        if(paises.equals("")){
            pais.setError("Ha dejado campos vacíos");
            return false;
        }
        String telefonos=telefono.getText().toString();
        if(telefonos.equals("")){
            telefono.setError("Ha dejado campos vacíos");
            return false;
        }
        String email=mail.getText().toString();
        if(email.equals("")){
            mail.setError("Ha dejado campos vacíos");
            return false;
        }
        String fechas=fecha.getText().toString();
        if(fechas.equals("")){
            mail.setError("Ha dejado campos vacíos");
            return false;
        }


        return true;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        //se usa para implementar el metodo DatePicker
        EditText fecha  = (EditText)findViewById(R.id.birthDate);
        fecha.setText(new StringBuilder().append(year).append("/").append(monthOfYear).append("/").append(dayOfMonth));
    }
    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {
        //Se usa para la seleccion del spinner, que item ses pueden seleccionar
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        }
        public void onNothingSelected(AdapterView<?> parent) {
            Spinner pasatiempo = (Spinner) findViewById(R.id.hobbies);
            pasatiempo.setOnItemSelectedListener(this);
        }
    }
}
