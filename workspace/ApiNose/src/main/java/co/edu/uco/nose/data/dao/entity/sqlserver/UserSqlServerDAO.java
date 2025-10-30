package co.edu.uco.nose.data.dao.entity.sqlserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.data.dao.entity.UserDAO;
import co.edu.uco.nose.entity.CityEntity;
import co.edu.uco.nose.entity.CountryEntity;
import co.edu.uco.nose.entity.IdTypeEntity;
import co.edu.uco.nose.entity.StateEntity;
import co.edu.uco.nose.entity.UserEntity;

public final class UserSqlServerDAO extends SqlConnection implements UserDAO {

	public UserSqlServerDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public void create(final UserEntity entity) {

		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

		final var sql = new StringBuilder();
		sql.append(
				"INSERT INTO Usuario(id, tipoIdentificacion, numeroIdentificacion, primerNombre, segundoNombre, primerApellido, segundoApellido, ciudadResidencia, correoElectronico, numeroTelefonoMovil, correoElectronicoConfirmado, numeroTelefonoMovilConfirmado) ");
		sql.append("SELECT ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?");

		try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {

			preparedStatement.setObject(1, entity.getId());
			preparedStatement.setObject(2, entity.getIdType().getId());
			preparedStatement.setString(3, entity.getIdNumber());
			preparedStatement.setString(4, entity.getFirstName());
			preparedStatement.setString(5, entity.getSecondName());
			preparedStatement.setString(6, entity.getFirstSurname());
			preparedStatement.setString(7, entity.getSecondSurname());
			preparedStatement.setObject(8, entity.getHomeCity().getId());
			preparedStatement.setString(9, entity.getEmail());
			preparedStatement.setString(10, entity.getMobileNumber());
			preparedStatement.setBoolean(11, entity.isEmailConfirmed());
			preparedStatement.setBoolean(12, entity.isMobileNumberConfirmed());

			preparedStatement.executeUpdate();

		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema tratando de registrar la información del nuevo usuario. Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema";
			var technicalMessage = "Se ha presentado un problema al tratar de ejecutar el proceso de creación de un nuevo usuario. Por favor valide que la base de datos este disponible y que la conexión este activa. Detalle técnico del error: "
					+ exception.getMessage();
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "Se ha presentado un problema INESPERADO tratando de registrar la información del nuevo usuario. Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema";
			var technicalMessage = "Se ha presentado un problema inesperado al tratar de ejecutar el proceso de creación de un nuevo usuario. Por favor valide que la base de datos este disponible y que la conexión este activa. Detalle técnico del error: "
					+ exception.getMessage();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}

	@Override
	public void update(final UserEntity entity) {
		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

		final var sql = new StringBuilder();
		sql.append("UPDATE  Usuario ");
		sql.append("SET     tipoIdentificacion = ?, ");
		sql.append("        numeroIdentificacion = ?, ");
		sql.append("        primerNombre = ?, ");
		sql.append("        segundoNombre = ?, ");
		sql.append("        primerApellido = ?, ");
		sql.append("        segundoApellido = ?, ");
		sql.append("        ciudadResidencia = ?, ");
		sql.append("        correoElectronico = ?, ");
		sql.append("        numeroTelefonoMovil = ?, ");
		sql.append("        correoElectronicoConfirmado = ?, ");
		sql.append("        numeroTelefonoMovilConfirmado = ? ");
		sql.append("WHERE   id = ?");

		try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {

			preparedStatement.setObject(1, entity.getIdType().getId());
			preparedStatement.setString(2, entity.getIdNumber());
			preparedStatement.setString(3, entity.getFirstName());
			preparedStatement.setString(4, entity.getSecondName());
			preparedStatement.setString(5, entity.getFirstSurname());
			preparedStatement.setString(6, entity.getSecondSurname());
			preparedStatement.setObject(7, entity.getHomeCity().getId());
			preparedStatement.setString(8, entity.getEmail());
			preparedStatement.setString(9, entity.getMobileNumber());
			preparedStatement.setBoolean(10, entity.isEmailConfirmed());
			preparedStatement.setBoolean(11, entity.isMobileNumberConfirmed());
			preparedStatement.setObject(12, entity.getId());

			preparedStatement.executeUpdate();

		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema tratando de modificar la información del usuario. Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema";
			var technicalMessage = "Se ha presentado un problema al tratar de ejecutar el proceso de modificación de un nuevo usuario. Por favor valide que la base de datos este disponible y que la conexión este activa. Detalle técnico del error: "
					+ exception.getMessage();
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "Se ha presentado un problema no esperado tratando de modificar la información del usuario. Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema";
			var technicalMessage = "Se ha presentado un problema no esperado al tratar de ejecutar el proceso de modificación del usuario. Por favor valide que la base de datos este disponible y que la conexión este activa. Detalle técnico del error: "
					+ exception.getMessage();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}

	@Override
	public void delete(final UUID id) {
		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

		final var sql = new StringBuilder();
		sql.append("DELETE ");
		sql.append("FROM  Usuario ");
		sql.append("WHERE id = ?");

		try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {

			preparedStatement.setObject(1, id);
			preparedStatement.executeUpdate();

		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema tratando de eliminar la información del usuario. Por favor intentar de nuevo y si el problema persiste contacte al administrador del sistema";
			var technicalMessage = "Se ha presentado un problema al tratar de ejecutar el proceso de eliminación del usuario. Por favor validar que la base de datos este disponible y que la conexión este activa. Detalle técnico del error: "
					+ exception.getMessage();
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "Se ha presentado un problema no esperado tratando de eliminar la información del usuario. Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema";
			var technicalMessage = "Se ha presentado un problema no esperado al tratar de ejecutar el proceso de eliminación del nuevo usuario. Por favor valide que la base de datos este disponible y que la conexión este activa. Detalle técnico del error: "
					+ exception.getMessage();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}

	@Override
	public UserEntity findById(final UUID id) {
		return findByFilter(new UserEntity(id)).stream().findFirst().orElse(new UserEntity());
	}

	@Override
	public List<UserEntity> findAll() {
		return findByFilter(new UserEntity());
	}

	@Override
	public List<UserEntity> findByFilter(final UserEntity filterEntity) {

		var parametersList = new ArrayList<Object>();
		var sql = createSentenceFindByFilter(filterEntity, parametersList);

		try (var preparedStatement = this.getConnection().prepareStatement(sql)) {

			for (int index = 0; index < parametersList.size(); index++) {
				preparedStatement.setObject(index + 1, parametersList.get(index));
			}

			return executeSentenceFindByFilter(preparedStatement);
		} catch (final NoseException exception) {
			throw exception;
		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema tratando de ejecutar la operacón de consulta de información de los usuarios. Por favor intentar de nuevo y si el problema persiste contacte al administrador del sistema";
			var technicalMessage = "Se ha presentado un problema al tratar de preparar la sentencia sql de consulta de información de los usuarios. Por favor valid que la base de datos este disponible y que la conexión este activa. Detalle técnico del error: "
					+ exception.getMessage();
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "Se ha presentado un problema tratando de ejecutar la operacón de consulta de información de los usuarios. Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema";
			var technicalMessage = "Se ha presentado un problema INESPERADO al tratar de preparar la sentencia sql de consulta de información de los usuarios. Por favor valide que la base de datos este disponible y que la conexión este activa. Detalle técnico del error: "
					+ exception.getMessage();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}

	private String createSentenceFindByFilter(final UserEntity filterEntity, final List<Object> parametersList) {
		final var sql = new StringBuilder();

		sql.append("SELECT     u.id, ");
		sql.append("           ti.id AS idTipoIdentificacion, ");
		sql.append("           ti.nombre AS nombreTipoIdentificacion, ");
		sql.append("           u.numeroIdentificacion, ");
		sql.append("           u.primerNombre, ");
		sql.append("           u.segundoNombre, ");
		sql.append("           u.primerApellido, ");
		sql.append("           u.segundoApellido, ");
		sql.append("           c.id AS idCiudadResidencia, ");
		sql.append("           c.nombre AS nombreCiudadResidencia, ");
		sql.append("           d.id AS idDepartamentoCiudadResidencia, ");
		sql.append("           d.nombre AS nombreDepartamentoCiudadResidencia, ");
		sql.append("           p.id AS idPaisDepartamentoCiudadResidencia, ");
		sql.append("           p.nombre AS nombrePaisDepartamentoCiudadResidencia, ");
		sql.append("           u.correoElectronico, ");
		sql.append("           u.numeroTelefonoMovil, ");
		sql.append("           u.correoElectronicoConfirmado, ");
		sql.append("           u.numeroTelefonoMovilConfirmado ");
		sql.append("FROM       Usuario AS u ");
		sql.append("INNER JOIN TipoIdentificacion AS ti ON u.tipoIdentificacion = ti.id ");
		sql.append("INNER JOIN Ciudad AS c ON u.ciudadResidencia = c.id ");
		sql.append("INNER JOIN Departamento AS d ON c.departamento = d.id ");
		sql.append("INNER JOIN Pais AS p ON d.pais = p.id ");

		createWhereClauseFindByFilter(sql, parametersList, filterEntity);

		return sql.toString();
	}

	private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parametersList,
			final UserEntity filterEntity) {

		var filterEntityValidated = ObjectHelper.getDefault(filterEntity, new UserEntity());
		final var conditions = new ArrayList<String>();

		addCondition(conditions, parametersList,
				!UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getId()), "u.id = ?",
				filterEntityValidated.getId());

		addCondition(conditions, parametersList,
				!UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getIdType().getId()),
				"u.tipoIdentificacion = ?", filterEntityValidated.getIdType().getId());

		addCondition(conditions, parametersList, !TextHelper.isEmptyWithTrim(filterEntityValidated.getIdNumber()),
				"u.numeroIdentificacion = ?", filterEntityValidated.getIdNumber());

		addCondition(conditions, parametersList, !TextHelper.isEmptyWithTrim(filterEntityValidated.getFirstName()),
				"u.primerNombre = ?", filterEntityValidated.getFirstName());

		addCondition(conditions, parametersList, !TextHelper.isEmptyWithTrim(filterEntityValidated.getSecondName()),
				"u.segundoNombre = ?", filterEntityValidated.getSecondName());

		addCondition(conditions, parametersList, !TextHelper.isEmptyWithTrim(filterEntityValidated.getFirstSurname()),
				"u.primerApellido = ?", filterEntityValidated.getFirstSurname());

		addCondition(conditions, parametersList, !TextHelper.isEmptyWithTrim(filterEntityValidated.getSecondSurname()),
				"u.segundoApellido = ?", filterEntityValidated.getSecondSurname());

		addCondition(conditions, parametersList,
				!UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getHomeCity().getId()),
				"u.ciudadResidencia = ?", filterEntityValidated.getHomeCity().getId());

		addCondition(conditions, parametersList, !TextHelper.isEmptyWithTrim(filterEntityValidated.getEmail()),
				"u.correoElectronico = ?", filterEntityValidated.getEmail());

		addCondition(conditions, parametersList, !TextHelper.isEmptyWithTrim(filterEntityValidated.getMobileNumber()),
				"u.numeroTelefonoMovil = ?", filterEntityValidated.getMobileNumber());

		addCondition(conditions, parametersList, !filterEntityValidated.isEmailConfirmedIsDefaultValue(),
				"u.correoElectronicoConfirmado = ?", filterEntityValidated.isEmailConfirmed());

		addCondition(conditions, parametersList, !filterEntityValidated.isMobileNumberConfirmedIsDefaultValue(),
				"u.numeroTelefonoMovilConfirmado = ?", filterEntityValidated.isMobileNumberConfirmed());

		if (!conditions.isEmpty()) {
			sql.append(" WHERE ");
			sql.append(String.join(" AND ", conditions));
		}
	}

	private void addCondition(final List<String> conditions, final List<Object> parametersList, final boolean condition,
			final String clause, final Object value) {
		if (condition) {
			conditions.add(clause);
			parametersList.add(value);
		}
	}

	private List<UserEntity> executeSentenceFindByFilter(final PreparedStatement preparedStatement) {

		var listUser = new ArrayList<UserEntity>();

		try (var resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				var idType = new IdTypeEntity();
				idType.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idTipoIdentificacion")));
				idType.setName(resultSet.getString("nombreTipoIdentificacion"));

				var country = new CountryEntity();
				country.setId(UUIDHelper.getUUIDHelper()
						.getFromString(resultSet.getString("idPaisDepartamentoCiudadResidencia")));
				country.setName(resultSet.getString("nombrePaisDepartamentoCiudadResidencia"));

				var state = new StateEntity();
				state.setCountry(country);
				state.setId(UUIDHelper.getUUIDHelper()
						.getFromString(resultSet.getString("idDepartamentoCiudadResidencia")));
				state.setName(resultSet.getString("nombreDepartamentoCiudadResidencia"));

				var city = new CityEntity();
				city.setState(state);
				city.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idCiudadResidencia")));
				city.setName(resultSet.getString("nombreCiudadResidencia"));

				var user = new UserEntity();
				user.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
				user.setIdType(idType);
				user.setFirstName(resultSet.getString("primerNombre"));
				user.setSecondName(resultSet.getString("segundoNombre"));
				user.setFirstSurname(resultSet.getString("primerApellido"));
				user.setSecondSurname(resultSet.getString("segundoApellido"));
				user.setHomeCity(city);
				user.setEmail(resultSet.getString("correoElectronico"));
				user.setMobileNumber(resultSet.getString("numeroTelefonoMovil"));
				user.setEmailConfirmed(resultSet.getBoolean("correoElectronicoConfirmado"));
				user.setMobileNumberConfirmed(resultSet.getBoolean("numeroTelefonoMovilConfirmado"));

				listUser.add(user);
			}
		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema tratando de ejecutar la operacón de consulta de información de los usuarios. Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema";
			var technicalMessage = "Se ha presentado un problema al tratar de ejecutar la consulta y recuperar los resultados de los usuarios. Por favor valide que la base de datos este disponible y que la conexión este activa. Detalle técnico del error: "
					+ exception.getMessage();
			throw NoseException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "Se ha presentado un problema tratando de ejecutar la operacón de consulta de información de los usuarios. Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema";
			var technicalMessage = "Se ha presentado un problema inesperado al tratar de ejecutar la consulta y recuperar los resultados de los usuarios deseados. Por favor valide que la base de datos este disponible y que la conexión este activa. Detalle técnico del error: "
					+ exception.getMessage();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}

		return listUser;
	}

}
