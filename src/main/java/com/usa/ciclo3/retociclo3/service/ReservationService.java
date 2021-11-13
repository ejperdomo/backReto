package com.usa.ciclo3.retociclo3.service;

import com.usa.ciclo3.retociclo3.model.Reservation;
import com.usa.ciclo3.retociclo3.reports.CounterClients;
import com.usa.ciclo3.retociclo3.reports.StatusReservations;
import com.usa.ciclo3.retociclo3.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll(){
        return reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int idReservation){
        return reservationRepository.getReservation(idReservation);
    }

    public Reservation save(Reservation reservation){
        if(reservation.getIdReservation() == null){
            return  reservationRepository.save(reservation);
        }else{
            Optional<Reservation> r = reservationRepository.getReservation(reservation.getIdReservation());
            if(r.isEmpty()){
                return reservationRepository.save(reservation);
            }else{
                return  reservation;
            }
        }
    }

    public Reservation update(Reservation reservation) {
        if (reservation.getIdReservation() != null) {
            Optional<Reservation> e = reservationRepository.getReservation(reservation.getIdReservation());
            if (!e.isEmpty()) {
                if (reservation.getStartDate() != null) {
                    e.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate() != null) {
                    e.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getStatus() != null) {
                    e.get().setStatus(reservation.getStatus());
                }
                reservationRepository.save(e.get());
                return e.get();
            } else {
                return reservation;
            }
        } else {
            return reservation;
        }
    }

    public boolean deleteReservation(int idReservation) {
        Boolean aBoolean = getReservation(idReservation).map(reservation -> {
            reservationRepository.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    public StatusReservations getReservationStatusReport(){
        List<Reservation>completed=reservationRepository.getReservationByStatus("completed");
        List<Reservation>cancelled=reservationRepository.getReservationByStatus("cancelled");
        return new StatusReservations(completed.size(),cancelled.size());
    }

    public List<Reservation> getReservationPeriod(String dateA, String dateB){
        SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd");
        Date aDate= new Date();
        Date bDate= new Date();

        try {
            aDate = parser.parse(dateA);
            bDate = parser.parse(dateB);
        } catch (ParseException evt){
            evt.printStackTrace();
        }
        if(aDate.before(bDate)){
            return  reservationRepository.getReservationPeriod(aDate, bDate);
        }else{
            return  new ArrayList<>();
        }
    }

    public List<CounterClients> getTopClients(){
        return  reservationRepository.getTopClients();
    }

}
