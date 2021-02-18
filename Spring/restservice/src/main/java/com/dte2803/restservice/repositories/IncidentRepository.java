package com.dte2803.restservice.repositories;

import com.dte2803.restservice.entities.Incident;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Repository to store Incident entities
 * Stored in memory with an arraylist
 */
@Repository
public class IncidentRepository implements BaseRepository<Incident, Long> {
    private List<Incident> incidents;
    private long count = 1; //Keeps track of current ID, instead of a database which automatically increments and assigns id

    public Incident findById(long id) {
        if(incidents == null) return null;
        for(Incident i : incidents) {
            if(i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    public void save(Incident incident) {
        if(incidents==null) incidents = new ArrayList<Incident>();
        incident.setId(count);
        incidents.add(incident);
        count++;
    }

    /**
     * finds all the incidens for a single user and returns an arraylist
     * @param username
     * @return
     */
    public List<Incident> getAllCasesForUser(String username) {
        List<Incident> userIncidents = new ArrayList<Incident>();
        if(incidents== null)
        {
        	return new ArrayList<Incident>();
        }
        for(Incident i : incidents) {
            if(i.getUsername() == username) {
                userIncidents.add(i);
            }
        }
        return userIncidents;
    }

    public List<Incident> getAll() {
        return incidents;
    }

    /**
     * edits incident object if it exists
     * @param incident
     */
    public void put(Incident incident) {
        for(Incident i : incidents) {
            if(i.getId()==incident.getId()) {
                int n = incidents.indexOf(i);
                incidents.set(n,incident);
            }
        }
    }

    /**
     * deletes incident if it exists
     * @param id
     */
    public void delete(long id) {
        Iterator<Incident> iter = incidents.iterator();
        int n = -1;
        while(iter.hasNext()) {
            Incident inc = iter.next();
            if(inc.getId()==id) {
                n = incidents.indexOf(inc);
            }
        }

        if(n!=-1) incidents.remove(n);
    }

    /**
     * checks if an incident exists
     * @param id
     * @return
     */
    public boolean exists(long id) {
        if(incidents == null) return false;
        for(Incident i : incidents) {
            if(i.getId()==id) {
                return true;
            }
        }
        return false;
    }

}
