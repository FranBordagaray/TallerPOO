package Modelo;

public class PruebaCliente {

	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		Tarjeta tarjeta = new Tarjeta();

		// Establecer valores cliente
		cliente.setIdCliente(1);
		cliente.setNombre("Juan");
		cliente.setApellido("Perez");
		cliente.setDomicilio("Calle Falsa 123");
		cliente.setTelefono("123456789");
		cliente.setEmail("juan.perez@example.com");
		cliente.setUsuario("juanperez");
		cliente.setContrasenia("contraseniaSegura");
		cliente.setTarjeta(tarjeta);

		// Establecer valores tarjeta
		tarjeta.setTitular(cliente.getNombre() + " " + cliente.getApellido());
		tarjeta.setEmisor("Visa");
		tarjeta.setNroTarjeta("1234567812345678");
		tarjeta.setCodVerificacion(195);

		System.out.print(cliente);
	}
}