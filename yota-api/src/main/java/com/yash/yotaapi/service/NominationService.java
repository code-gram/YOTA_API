package com.yash.yotaapi.service;
 
import com.yash.yotaapi.domain.Nomination;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
 
/**
* Service interface for managing Nomination entities.
* Declares methods to perform CRUD operations and search by employee name.
* @author raghav.muchhal
*/
@Service
public interface NominationService {
 
    /**
     * Create a new nomination.
     *
     * @param nomination The nomination object to be created.
     * @return The created nomination object.
     */
    void createNomination(List<Nomination> nominations);
 
    /**
     * Retrieve all nominations.
     *
     * @return List of all nominations.
     */
    List<Nomination> getAllNominations();
 
    /**
     * Get a nomination by its ID.
     *
     * @param nominationId The ID of the nomination to retrieve.
     * @return The nomination with the given ID.
     */
    Nomination getNominationById(Long nominationId);
 
    /**
     * Update a nomination.
     *
     * @param nominationId     The ID of the nomination to update.
     * @param updatedNomination The updated nomination object.
     * @return The updated nomination object.
     */
    Nomination updateNomination(Long nominationId, Nomination updatedNomination);
 
    /**
     * Delete a nomination by its ID.
     *
     * @param nominationId The ID of the nomination to delete.
     */
    void deleteNomination(Long nominationId);
 
    /**
     * Find nominations by employee name.
     *
     * @param employeeName The name of the employee.
     * @return List of nominations associated with the given employee name.
     */
    List<Nomination> findByEmployeeName(String employeeName);
    
    public void saveExcel(MultipartFile file);
}