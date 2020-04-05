package com.udgs123.dkdn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.annotation.SuppressLint;
import android.widget.RadioButton;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    EditText edtNombres, edtCorreo;
    boolean gioitinh;
    Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtNombres = (EditText) findViewById(R.id.edtNombres);
        edtCorreo = (EditText) findViewById(R.id.edtCorreo);
        btnAgregar = (Button) findViewById(R.id.btnAgregarUsuario);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarUsuario();
            }
        });
    }
    public Connection conexionBD() {
        Connection connection = null;
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.4;databaseName=Developeru;user=sa;password=123;");
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return connection;
    }
    public void agregarUsuario() {
        try {
            PreparedStatement pst = conexionBD().prepareStatement("insert into usuarios values(?,?)");
            pst.setString(1, edtNombres.getText().toString().trim());
            pst.setString(2,edtCorreo.getText().toString().trim());
            pst.executeUpdate();
            Toast.makeText(getApplicationContext(),"Dang ki thanh cong", Toast.LENGTH_SHORT).show();

        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }
}
