package co.edu.uco.nose.data.dao.factory;

import java.sql.Connection;
import java.sql.SQLException;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.nose.data.dao.entity.CityDAO;
import co.edu.uco.nose.data.dao.entity.CountryDAO;
import co.edu.uco.nose.data.dao.entity.IdTypeDAO;
import co.edu.uco.nose.data.dao.entity.StateDAO;
import co.edu.uco.nose.data.dao.entity.UserDAO;
import co.edu.uco.nose.data.dao.factory.sqlserver.SqlServerDAOFactory;

public abstract class DAOFactory {

	protected Connection connection;
	protected static FactoryEnum factory = FactoryEnum.SQLSERVER;

	public static DAOFactory getFactory() {
	
		if (FactoryEnum.SQLSERVER.equals(factory)) {
			return new SqlServerDAOFactory();
		} else {
			var userMessage = "Factoría no iniciada";
			var technicalMessage = "Factoría no válida";
			throw NoseException.create(userMessage, technicalMessage);
		}
	}

	public abstract CityDAO getCityDAO();

	public abstract CountryDAO getCountryDAO();

	public abstract IdTypeDAO getIdTypeDAO();

	public abstract StateDAO getStateDAO();

	public abstract UserDAO getUserDAO();

	protected abstract void openConnection();

	public final void initTransaction() {
		SqlConnectionHelper.ensureTransactionIsNotStarted(connection);

		try {
			connection.setAutoCommit(false);
		} catch (final SQLException exception) {
			var userMessage = "";
			var technicalMessage = "";
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "";
			var technicalMessage = "";
			throw NoseException.create(exception, userMessage, technicalMessage);
		}

	}

	public final void commitTransaction() {
		SqlConnectionHelper.ensureTransactionIsStarted(connection);

		try {
			connection.commit();
		} catch (final SQLException exception) {
			var userMessage = "";
			var technicalMessage = "";
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "";
			var technicalMessage = "";
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}

	public final void rollbackTransaction() {
		SqlConnectionHelper.ensureTransactionIsStarted(connection);

		try {
			connection.rollback();
		} catch (final SQLException exception) {
			var userMessage = "";
			var technicalMessage = "";
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "";
			var technicalMessage = "";
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}

	public final void closeConnection() {
		SqlConnectionHelper.ensureConnectionIsOpen(connection);

		try {
			connection.close();
		} catch (final SQLException exception) {
			var userMessage = "";
			var technicalMessage = "";
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "";
			var technicalMessage = "";
			throw NoseException.create(exception, userMessage, technicalMessage);
		}

	}

}
