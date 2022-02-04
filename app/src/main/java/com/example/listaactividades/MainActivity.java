package com.example.listaactividades;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listaactividades.db.DBProductos;
import com.example.listaactividades.db.DbHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private double carrito = 0.0;
    private ListView Cocas;
    private Button Pagar;
    private Button Limpiar;
    private Button BtnCarrito;
    private TextView Cantidad;
    private CustomeAdapter customeAdapter;
    private CustomeAdapter Custome;
    private ArrayList<ProductsTreeModel> imageModelArrayList;
    private ArrayList<ProductsTreeModel> imageCocas;
    private int[] myImageList = new int[]{R.drawable.benz, R.drawable.bike,
            R.drawable.car,R.drawable.carrera
            ,R.drawable.ferrari,R.drawable.harly,
            R.drawable.lamborghini,R.drawable.silver};
    private String[] myImageNameList = new String[]{"Refrescos", "Pan",
            "Frituras","Dulces"
            ,"Abarrotes","Cigarros",
            "Cervezas","Servicios"};
    private double[] myPriceList = new double[]{15.00, 20.00,
            14.00,7.50
            ,23.00,75.00,
            25.00,60.00};
    private String[] myDescList = new String[]{"Medio Litro", "Bimbo",
            "Chetos","Cracups"
            ,"lechera","Malboro",
            "Corona","Recarga"};

    private String [] myArticles = new String[]{
              "Coca cola", "Pepsi", "Peña fiel","Jarritos"
    };

    private String [] mySize = new String[]{
            "500 ml", "600 ml", "1 lt", "1.5 lt", "2 lt", "3 lt"
    };


    ArrayList<Integer> CarroLista = new ArrayList<Integer>();

    private int[] Carrito = new int[]{


    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB();
        lv = (ListView) findViewById(R.id.listView);
       Cocas = (ListView) findViewById(R.id.Cocas);
       Pagar = findViewById(R.id.btnPagar);
       Cantidad = (TextView)findViewById(R.id.txtCantidad);
       Limpiar = findViewById(R.id.btnLimpiar);
       BtnCarrito = findViewById(R.id.btnCarrito);



        imageModelArrayList = populateList();
        imageCocas = CarritoLista();

        // Log.d("hjhjh",imageModelArrayList.size()+"");
        customeAdapter = new CustomeAdapter(this,imageModelArrayList);
        Custome = new CustomeAdapter(this, imageCocas);
        lv.setAdapter(customeAdapter);




        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Object o = lv.getItemAtPosition(i);
                carrito = carrito + myPriceList[i];
                Cantidad.setText("Cantidad: "+carrito);
               // Toast.makeText(MainActivity.this,"Seleccinado"+ o.toString(),Toast.LENGTH_SHORT).show();
                CarroLista.add(i);
                Toast.makeText(MainActivity.this,("Seleccinado "+ CarroLista.size()+ "Posicion " + i).toString(),Toast.LENGTH_SHORT).show();

            }
        });

        Pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(CarroLista.size() != 0){
                    imageCocas = CarritoLista();
                    Custome = new CustomeAdapter(MainActivity.this, imageCocas);
                    Cocas.setAdapter(Custome);
                    lv.setVisibility(View.INVISIBLE);
                    Cocas.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(MainActivity.this,"No ha agregado productos".toString(),Toast.LENGTH_SHORT).show();
                }


            }
        });


        Limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carrito = 0.0;
                Cantidad.setText("Cantidad: "+carrito);
                CarroLista.clear();
                Cocas.setVisibility(View.INVISIBLE);
                lv.setVisibility(View.VISIBLE);
            }
        });

        BtnCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBProductos base = new DBProductos(MainActivity.this);
                for(int i = 0; i < myPriceList.length; i++) {

                    if(base.insertarProductos(myImageNameList[i], myImageList[i],myPriceList[i],1,1) == 1) {
                        Toast.makeText(MainActivity.this, "Se registró el producto",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Error al registar el producto",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }





    private void DB(){
        DbHelper base = new DbHelper(MainActivity.this);
        SQLiteDatabase db =  base.getWritableDatabase();

        if(db != null){
            Toast.makeText(MainActivity.this, "Se creo la base de datos con Exito", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this, "ERROR al crear la Base de datos", Toast.LENGTH_LONG).show();
        }
    }

    private void InsertarBase(){

    }

    private ArrayList<ProductsTreeModel> populateList(){


       // String nombre, Double imagen, float precio, int categoria , int padre

        ArrayList<ProductsTreeModel> list = new ArrayList<>();

        for(int i = 0; i < myPriceList.length; i++){
            ProductsTreeModel imageModel = new ProductsTreeModel();
            imageModel.setName(myImageNameList[i]);
            imageModel.setPrice(myPriceList[i]);
            imageModel.setDescr(myDescList[i]);
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }

        return list;

    }

    private ArrayList<ProductsTreeModel> cocasLista(){
        ArrayList<ProductsTreeModel> list = new ArrayList<>();

        for(int i = 0; i<myArticles.length; i++){
            for(int j = 0; j<mySize.length; j++){
                ProductsTreeModel imageModel = new ProductsTreeModel();
                imageModel.setName(myImageNameList[i]);
                imageModel.setName(myArticles[i]);
                imageModel.setDescr(mySize[j]);
                imageModel.setImage_drawable(myImageList[i]);
                list.add(imageModel);
            }

        }

        return list;
    }

    private ArrayList <ProductsTreeModel> CarritoLista(){

        ArrayList<ProductsTreeModel> list = new ArrayList<>();

        for(Integer i : CarroLista)
        {

            ProductsTreeModel imageModel = new ProductsTreeModel();
            imageModel.setName(myImageNameList[CarroLista.get(i)]);
            imageModel.setPrice(myPriceList[CarroLista.get(i)]);
            imageModel.setDescr(myDescList[CarroLista.get(i)]);
            imageModel.setImage_drawable(myImageList[CarroLista.get(i)]);
            list.add(imageModel);
        }



        return list;

    }







}