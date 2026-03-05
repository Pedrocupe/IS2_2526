package es.unican.is2;

import java.util.ArrayList;
import java.util.List;

public class GestionSeguros implements IGestionClientes, IInfoSeguros, IGestionSeguros {
    
    List<Cliente> clientes = new ArrayList<Cliente>();
    List<Seguro> seguros = new ArrayList<Seguro>();

    IClientesDAO cDao;
    ISegurosDAO sDao;
    
    public GestionSeguros(IClientesDAO cDao,ISegurosDAO sDao) {
        this.cDao = cDao;
        this.sDao = sDao;
    }

    @Override
    /**
	 * Persiste un nuevo cliente
	 * @param c Cliente que desea persistir
	 * @return El cliente persitido
	 * 		   null si no se persiste porque ya existe
	  * @throws DataAccessException si se produce un error 
	 * en el acceso a la base de datos
	 */
	public Cliente nuevoCliente(Cliente c) throws DataAccessException {
        if (cDao.cliente(c.getDni()) != null) {
            return null;
        }
        cDao.creaCliente(c);
        return c;
    }
	
    @Override
	/**
	 * Retorna el cliente con el dni indicado
	 * @param dni 
	 * @return El cliente
	 * 		   null si no se encuentra
	 * @throws DataAccessException si se produce un error 
	 * en el acceso a la base de datos
	 */
	public Cliente cliente(String dni) throws DataAccessException {
        return cDao.cliente(dni);
    }

    @Override
    /**
	 * Retorna el seguro cuya matricula se indica
	 * @param matricula Identificador del seguro
	 * @return El seguro indicado
	 * 	       null si no existe
	* @throws DataAccessException si se produce un error 
	 * en el acceso a la base de datos
	 */
	public Seguro seguro(String matricula) throws DataAccessException {
        return sDao.seguroPorMatricula(matricula);
    }
	
    @Override
	/**
	 * Elimina el cliente con el dni indicado
	 * @param dni
	 * @return El cliente eliminado
	 *         null si no se encuentra el cliente
	 * @throws DataAccessException si se produce un error 
	 * en el acceso a la base de datos
	 */
	public Cliente bajaCliente(String dni) throws DataAccessException {
        if (cDao.cliente(dni) != null) {
            return cDao.eliminaCliente(dni);
        }
        return null;
    }
   
    @Override
    /**
	 * Agrega un nuevo seguro al cliente cuyo dni se indica.
	 * @param s Seguro que desea agregar
	 * @param dni DNI del cliente
	 * @return El seguro agregado
	 * 		   null si no se agrega porque no se encuentra el cliente
	 * @throws OperacionNoValida si el seguro ya existe
	 * @throws DataAccessException si se produce un error 
	 * en el acceso a la base de datos
	 */
	public Seguro nuevoSeguro(Seguro s, String dni) throws OperacionNoValida, DataAccessException {
        if (sDao.seguro(s.getId()) != null) {
            throw new OperacionNoValida("Seguro ya existente");
        } 
        Cliente c = cDao.cliente(dni);
        if (c == null) {
            return null;
        }
        List<Seguro> seguros = c.getSeguros();
        seguros.add(s);
        c.setSeguros(seguros);
        return s;
    }
	
    @Override
	/**
	 * Elimina el seguro cuya matricula se indica y 
	 * que pertenece al cliente cuyo dni se indica
	 * @param matricula Identificador del seguro a eliminar
	 * @param dni DNI del propietario del seguro
 	 * @return El seguro eliminado
 	 *         null si el seguro o el cliente no existen
 	 * @throws OperacionNoValida si el seguro no pertenece al dni indicado
	 * @throws DataAccessException si se produce un error 
	 * en el acceso a la base de datos
	 */
	public Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida, DataAccessException {
        
        //Si el seguro no existe -> null
        Seguro s = sDao.seguroPorMatricula(matricula);
        if (s == null) {
            return null;
        } 

        Cliente c = cDao.cliente(dni);
        if (c == null) {
            return null;
        }
        List<Seguro> seguros = c.getSeguros();
        if (!seguros.contains(s)) {
            throw new OperacionNoValida("El seguro no pertenece al dni indicado");
        }
        seguros.remove(s);
        c.setSeguros(seguros);
        return s;
    }

    @Override
	/**
	 * Agrega o modifica el conductor adicional al seguro cuya matricula se indica
	 * @param matricula Identificador del seguro
	 * @param conductor Nombre del conductor adicional a agregar
 	 * @return El seguro modificado
 	 *         null si el seguro no existe
	 * @throws DataAccessException si se produce un error 
	 * en el acceso a la base de datos
	 */
	public Seguro anhadeConductorAdicional(String matricula, String conductor) throws DataAccessException {
        Seguro s = sDao.seguroPorMatricula(matricula);
        if (s == null) {
            return null;
        } 
        s.setConductorAdicional(conductor);
        return s;
    }



}
