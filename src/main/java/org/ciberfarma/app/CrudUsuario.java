package org.ciberfarma.app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.ciberfarma.modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CrudUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtnom;
	private JTextField txtape;
	private JTextField txtusu;
	private JTextField txtclave;
	private JTextField txtcod;
	private JTextArea txtS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrudUsuario frame = new CrudUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CrudUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mantenimiento Usuario");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 11, 188, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Codigo:");
		lblNewLabel_1.setBounds(10, 36, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setBounds(10, 61, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Apellido:");
		lblNewLabel_3.setBounds(10, 86, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("FechNac:");
		lblNewLabel_4.setBounds(10, 111, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Usuario:");
		lblNewLabel_5.setBounds(172, 36, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Clave:");
		lblNewLabel_6.setBounds(172, 61, 46, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Tipo:");
		lblNewLabel_7.setBounds(172, 86, 46, 14);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Estado:");
		lblNewLabel_8.setBounds(172, 111, 46, 14);
		contentPane.add(lblNewLabel_8);
		
		txtnom = new JTextField();
		txtnom.setBounds(55, 58, 107, 20);
		contentPane.add(txtnom);
		txtnom.setColumns(10);
		
		txtape = new JTextField();
		txtape.setColumns(10);
		txtape.setBounds(55, 83, 107, 20);
		contentPane.add(txtape);
		
		txtusu = new JTextField();
		txtusu.setColumns(10);
		txtusu.setBounds(216, 33, 107, 20);
		contentPane.add(txtusu);
		
		txtclave = new JTextField();
		txtclave.setColumns(10);
		txtclave.setBounds(216, 58, 107, 20);
		contentPane.add(txtclave);
		
		JComboBox cbotipo = new JComboBox();
		cbotipo.setBounds(216, 82, 107, 20);
		contentPane.add(cbotipo);
		
		JComboBox cboestado = new JComboBox();
		cboestado.setBounds(216, 107, 107, 20);
		contentPane.add(cboestado);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registrar();
			}
		});
		btnRegistrar.setBounds(333, 33, 89, 20);
		contentPane.add(btnRegistrar);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(333, 57, 89, 20);
		contentPane.add(btnActualizar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(333, 82, 89, 20);
		contentPane.add(btnEliminar);
		
		txtcod = new JTextField();
		txtcod.setColumns(10);
		txtcod.setBounds(55, 33, 107, 20);
		contentPane.add(txtcod);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarUsuario();
			}
		});
		btnBuscar.setBounds(333, 107, 89, 20);
		contentPane.add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 150, 314, 100);
		contentPane.add(scrollPane);
		
		txtS = new JTextArea();
		scrollPane.setViewportView(txtS);
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(333, 132, 89, 23);
		contentPane.add(btnListado);
	}

	protected void Registrar() {
		String nombre = leerNombre();
		
	}

	private String leerNombre() {
		if(!txtnom.getText().matches("")) {
			return null;
		}
		return txtnom.getText();
	}

	void listado() {
		// TODO Auto-generated method stub
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA_Clase01");
		EntityManager em = factory.createEntityManager();
		
		//TypedQuery<Usuario> consulta = em.createNamedQuery("Usuario.findAll", Usuario.class);
		//List<Usuario> lstUsuario = consulta.getResultList(); 
		TypedQuery<Usuario> consulta = em.createNamedQuery("Usuario.findAllWithType", Usuario.class);
		consulta.setParameter("xtipo", 1);
		List<Usuario> lstUsuario = consulta.getResultList(); 
		em.close();
		for(Usuario u : lstUsuario) {
			txtS.append(u.getCodigo() + "\t" + u.getNombre() + "\t" + u.getApellido() + "\n");
		}
	}

	void BuscarUsuario() {
		// leer el codigo
		int codigo = leerCodigo();
		//buscar en la tabla, para obtener un usuario
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA_Clase01");
		EntityManager em = factory.createEntityManager();
		
		Usuario u = em.find(Usuario.class, codigo);		
		em.close();
		
		if(u == null) {
			aviso("usuario "+ codigo +" no existe!!!");
		}else {
			txtnom.setText(u.getNombre());
			txtape.setText(u.getApellido());
		}
	}

	private void aviso(String msg) {
		JOptionPane.showMessageDialog(this, msg,"Aviso del Sistema", 2);
		
	}

	private int leerCodigo() {
		// TODO Auto-generated method stub
		return Integer.parseInt(txtcod.getText());
	}
}
