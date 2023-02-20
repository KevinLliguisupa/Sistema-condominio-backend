package com.restapi.siscondominio.control.persistence.repositories;

import com.restapi.siscondominio.control.persistence.entities.CtrLugar;
import com.restapi.siscondominio.control.persistence.entities.CtrReservacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface CtrReservacionRepository extends JpaRepository<CtrReservacion, Long>, JpaSpecificationExecutor<CtrReservacion> {
    Optional<CtrReservacion> findByLugAndResFechaAndResHoraInicioAndResHoraFin(CtrLugar lug, LocalDate resFecha, LocalTime resHoraInicio, LocalTime resHoraFin);

    List<CtrReservacion> findAllByResFechaGreaterThanEqualOrderByResFechaDesc(LocalDate resFecha);

    List<CtrReservacion> findByLugAndResFecha(CtrLugar lug, LocalDate resFecha);

}