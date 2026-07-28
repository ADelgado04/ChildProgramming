package co.edu.unicauca.child_programming_backend.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.edu.unicauca.child_programming_backend.DTO.input.ChildActivityDTOPeticion;
import co.edu.unicauca.child_programming_backend.DTO.input.CollaborativePatternDTOPeticion;
import co.edu.unicauca.child_programming_backend.DTO.input.CollaborativeProcessDTOPeticion;
import co.edu.unicauca.child_programming_backend.DTO.input.PracticeDTOPeticion;
import co.edu.unicauca.child_programming_backend.DTO.input.RoleDTOPeticion;
import co.edu.unicauca.child_programming_backend.DTO.input.RoundDTOPeticion;
import co.edu.unicauca.child_programming_backend.DTO.input.ThinkletDTOPeticion;
import co.edu.unicauca.child_programming_backend.DTO.output.ChildActivityDTORespuesta;
import co.edu.unicauca.child_programming_backend.DTO.output.CollaborativePatternDTORespuesta;
/*
 * import model-DTO-service        
*/
import co.edu.unicauca.child_programming_backend.DTO.output.CollaborativeProcessDTORespuesta;
import co.edu.unicauca.child_programming_backend.DTO.output.PracticeDTORespuesta;
import co.edu.unicauca.child_programming_backend.DTO.output.RoleDTORespuesta;
import co.edu.unicauca.child_programming_backend.DTO.output.RoundDTORespuesta;
import co.edu.unicauca.child_programming_backend.DTO.output.ThinkletDTORespuesta;
import co.edu.unicauca.child_programming_backend.DTO.output.full.ProcessFullDTORespuesta;
import co.edu.unicauca.child_programming_backend.exceptionControllers.exceptions.EntidadNoExisteException;
import co.edu.unicauca.child_programming_backend.services.FileStorageService;
import co.edu.unicauca.child_programming_backend.services.IChildActivityService;
import co.edu.unicauca.child_programming_backend.services.ICollaborativePatternService;
import co.edu.unicauca.child_programming_backend.services.ICollaborativeProcessService;
import co.edu.unicauca.child_programming_backend.services.IPracticeService;
import co.edu.unicauca.child_programming_backend.services.IRoleService;
import co.edu.unicauca.child_programming_backend.services.IRoundService;
import co.edu.unicauca.child_programming_backend.services.IThinkletService;

@RestController
@RequestMapping("/api")
@Validated
@CrossOrigin(origins = { "http://localhost:3000" })
public class ChildProgrammingRestController {
    
    @Autowired
	private ICollaborativeProcessService processService;

    @Autowired
    private IChildActivityService activityService;

    @Autowired
    private IRoundService roundService;

    @Autowired
    private IPracticeService practiceService;

    @Autowired
    private IThinkletService thinkletService;
    
    @Autowired
    private ICollaborativePatternService patternService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private FileStorageService fileStorageService;

    ///////////////////////////////////////////////////////////////////
    /// ColaborativeProcess
    ///////////////////////////////////////////////////////////////////
    
    @GetMapping("/colaborative_process/list")
    public ResponseEntity<List<CollaborativeProcessDTORespuesta>> getAllProcesses() {
        return ResponseEntity.ok(processService.getAllProcesses());
    }

    // Obtener un proceso por su ID
    @GetMapping("/colaborative_process/{id}")
    public ResponseEntity<CollaborativeProcessDTORespuesta> getProcessById(@PathVariable Integer id) {
        try {
            CollaborativeProcessDTORespuesta process = processService.getProcessById(id);
            return ResponseEntity.ok(process);
        } catch (EntidadNoExisteException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Crear un proceso
    @PostMapping(value = "/colaborative_process/create", consumes = "multipart/form-data")
    public ResponseEntity<CollaborativeProcessDTORespuesta> createProcess(
            @RequestParam("name_process") String name_process,
            @RequestParam("description_process") String description_process,
            @RequestParam(value = "version_process", required = false) String version_process,
            @RequestParam(value = "image", required = false) MultipartFile imageFile,
            @RequestParam(value = "imageUrl", required = false) String imageUrl) {

        String imagePath = null;

        //Si se sube un archivo físico, lo guardamos
        if (imageFile != null && !imageFile.isEmpty()) {
            imagePath = fileStorageService.save(imageFile);
        }

        //Si no hay archivo pero sí URL, la guardamos directamente
        else if (imageUrl != null && !imageUrl.isBlank()) {
            imagePath = imageUrl.trim();
        }

        //Crear el DTO
        CollaborativeProcessDTOPeticion dto = new CollaborativeProcessDTOPeticion();
        dto.setName_process(name_process);
        dto.setDescription_process(description_process);
        dto.setVersion_process(version_process);
        dto.setImage(imagePath); // puede ser ruta local o URL externa

        //Guardar en BD
        CollaborativeProcessDTORespuesta newProcess = processService.createProcess(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(newProcess);
    }

    //Actualizar un proceso
    @PatchMapping(value = "/colaborative_process/update/{id}", consumes = "multipart/form-data")
    public ResponseEntity<CollaborativeProcessDTORespuesta> updateProcess(
            @PathVariable Integer id,
            @RequestParam("name_process") String name_process,
            @RequestParam("description_process") String description_process,
            @RequestParam(value = "version_process", required = false) String version_process,
            @RequestParam(value = "image", required = false) MultipartFile imageFile,
            @RequestParam(value = "imageUrl", required = false) String imageUrl) {

        String imagePath = null;

        // Si se sube un archivo, lo guardamos
        if (imageFile != null && !imageFile.isEmpty()) {
            imagePath = fileStorageService.save(imageFile);
        }
        //Si no hay archivo pero hay URL
        else if (imageUrl != null && !imageUrl.isBlank()) {
            imagePath = imageUrl.trim();
        }

        //Construir DTO (igual que en create)
        CollaborativeProcessDTOPeticion dto = new CollaborativeProcessDTOPeticion();
        dto.setName_process(name_process);
        dto.setDescription_process(description_process);
        dto.setVersion_process(version_process);
        dto.setImage(imagePath);

        //Actualizar usando el servicio
        CollaborativeProcessDTORespuesta updated = processService.updateProcess(id, dto);

        return ResponseEntity.ok(updated);
    }

    //Eliminar un proceso
    @DeleteMapping("/colaborative_process/delete/{id}")
    public ResponseEntity<Void> deleteProcess(@PathVariable Integer id) {
        boolean deleted = processService.deleteProcess(id);
        return (deleted) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    ///////////////////////////////////////////////////////////////////
    //Imagen de CollaborativeProcess
    //////////////////////////////////////////////////////////////////
    @GetMapping("/processImages/{filename:.+}")
    public ResponseEntity<byte[]> getProcessImage(@PathVariable String filename) {
        byte[] imageData = fileStorageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .body(imageData);
    }
    ///////////////////////////////////////////////////////////////////
    //Obtener toda la informacion relacionada al proceso
    //////////////////////////////////////////////////////////////////
    @GetMapping("/colaborative_process/full/{id}")
    public ResponseEntity<ProcessFullDTORespuesta> getFullProcess(@PathVariable Integer id) {
        try {
            ProcessFullDTORespuesta fullProcess = processService.getFullProcess(id);
            return ResponseEntity.ok(fullProcess);
        } catch (EntidadNoExisteException e) {
            return ResponseEntity.notFound().build();
        }
    }
    ///////////////////////////////////////////////////////////////////
    /// ChildActivity
    ///////////////////////////////////////////////////////////////////
    @GetMapping("/child_activity/list")
    public ResponseEntity<List<ChildActivityDTORespuesta>> getAllActivities() {
        return ResponseEntity.ok(activityService.getAllActivities());
    }

    // Buscar por id
    @GetMapping("/child_activity/{id}")
    public ResponseEntity<ChildActivityDTORespuesta> getActivityById(@PathVariable Integer id) {
        ChildActivityDTORespuesta activity  = activityService.getActivityById(id);
        return ResponseEntity.ok(activity );
    }

    // Crear actividad
    @PostMapping("/child_activity/create")
    public ResponseEntity<ChildActivityDTORespuesta> createActivity(
            @RequestBody ChildActivityDTOPeticion request) {
        ChildActivityDTORespuesta newActivity = activityService.createActivity(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newActivity);
    }

    // Actualizar actividad
    @PatchMapping("/child_activity/update/{id}")
    public ResponseEntity<ChildActivityDTORespuesta> updateActivity(
            @PathVariable Integer id,
            @RequestBody ChildActivityDTOPeticion request) {
        ChildActivityDTORespuesta updatedActivity  = activityService.updateActivity(id, request);
        return ResponseEntity.ok(updatedActivity);
    }

    // Eliminar actividad
    @DeleteMapping("/child_activity/delete/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Integer id) {
        boolean deleted = activityService.deleteActivity(id);
        return (deleted) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    //obtener roles asociados a una actividad
    @GetMapping("/child_activity/{id}/roles")
    public ResponseEntity<ChildActivityDTORespuesta> getActivityRoles(@PathVariable Integer id) {
        return ResponseEntity.ok(activityService.getActivityWithRoles(id));
    }

    ///////////////////////////////////////////////////////////////////
    /// Round
    ///////////////////////////////////////////////////////////////////
    @GetMapping("/round/list")
    public ResponseEntity<List<RoundDTORespuesta>> getAllRounds() {
        return ResponseEntity.ok(roundService.getAllRounds());
    }

    @GetMapping("/round/{id}")
    public ResponseEntity<RoundDTORespuesta> getRoundById(@PathVariable Integer id) {
        RoundDTORespuesta round = roundService.getRoundById(id);
        return ResponseEntity.ok(round);
    }

    @PostMapping("/round/create")
    public ResponseEntity<RoundDTORespuesta> createRound(
            @RequestBody RoundDTOPeticion request) {
        RoundDTORespuesta newRound = roundService.createRound(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRound);
    }

    @PatchMapping("/round/update/{id}")
    public ResponseEntity<RoundDTORespuesta> updateRound(
            @PathVariable Integer id,
            @RequestBody RoundDTOPeticion request) {
        RoundDTORespuesta updatedRound = roundService.updateRound(id, request);
        return ResponseEntity.ok(updatedRound);
    }

    @DeleteMapping("/round/delete/{id}")
    public ResponseEntity<Void> deleteRound(@PathVariable Integer id) {
        boolean deleted = roundService.deleteRound(id);
        return (deleted) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("round/byProcess/{idProcess}")
    public ResponseEntity<List<RoundDTORespuesta>> getRoundsByProcess(@PathVariable Integer idProcess) {
        return ResponseEntity.ok(roundService.getRoundsByProcess(idProcess));
    }

    ///////////////////////////////////////////////////////////////////
    /// Practice
    ///////////////////////////////////////////////////////////////////

    // Listar todas las prácticas
    @GetMapping("/practice/list")
    public ResponseEntity<List<PracticeDTORespuesta>> getAllPractices() {
        return ResponseEntity.ok(practiceService.getAllPractices());
    }

    // Buscar práctica por id
    @GetMapping("/practice/{id}")
    public ResponseEntity<PracticeDTORespuesta> getPracticeById(@PathVariable Integer id) {
        PracticeDTORespuesta practice = practiceService.getPracticeById(id);
        return (practice != null) ? ResponseEntity.ok(practice) : ResponseEntity.notFound().build();
    }

    // Crear nueva práctica
    @PostMapping("/practice/create")
    public ResponseEntity<PracticeDTORespuesta> createPractice(
            @RequestBody PracticeDTOPeticion request) {
        PracticeDTORespuesta newPractice = practiceService.createPractice(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPractice);
    }

    // Actualizar práctica existente
    @PatchMapping("/practice/update/{id}")
    public ResponseEntity<PracticeDTORespuesta> updatePractice(
            @PathVariable Integer id,
            @RequestBody PracticeDTOPeticion request) {
        PracticeDTORespuesta updatedPractice = practiceService.updatePractice(id, request);
        return (updatedPractice != null) ? ResponseEntity.ok(updatedPractice) : ResponseEntity.notFound().build();
    }

    // Eliminar práctica
    @DeleteMapping("/practice/delete/{id}")
    public ResponseEntity<Void> deletePractice(@PathVariable Integer id) {
        boolean deleted = practiceService.deletePractice(id);
        return (deleted) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    /////////////////////////////////////////////////////////////////////
    /// Thinklet
    /////////////////////////////////////////////////////////////////////
    @GetMapping("/thinklet/list")
    public ResponseEntity<List<ThinkletDTORespuesta>> getAllThinklets() {
        return ResponseEntity.ok(thinkletService.getAllThinklets());
    }

    // Buscar por id
    @GetMapping("/thinklet/{id}")
    public ResponseEntity<ThinkletDTORespuesta> getThinkletById(@PathVariable Integer id) {
        ThinkletDTORespuesta thinklet = thinkletService.getThinkletById(id);
        return ResponseEntity.ok(thinklet);
    }

    // Crear thinklet
    @PostMapping("/thinklet/create")
    public ResponseEntity<ThinkletDTORespuesta> createThinklet(
            @RequestBody ThinkletDTOPeticion request) {
        ThinkletDTORespuesta newThinklet = thinkletService.createThinklet(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newThinklet);
    }

    // Actualizar thinklet
    @PatchMapping("/thinklet/update/{id}")
    public ResponseEntity<ThinkletDTORespuesta> updateThinklet(
            @PathVariable Integer id,
            @RequestBody ThinkletDTOPeticion request) {
        ThinkletDTORespuesta updatedThinklet = thinkletService.updateThinklet(id, request);
        return ResponseEntity.ok(updatedThinklet);
    }

    // Eliminar thinklet
    @DeleteMapping("/thinklet/delete/{id}")
    public ResponseEntity<Void> deleteThinklet(@PathVariable Integer id) {
        boolean deleted = thinkletService.deleteThinklet(id);
        return (deleted) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    ///////////////////////////////////////////////////////////////////
    /// CollaborativePattern
    ///////////////////////////////////////////////////////////////////
    @GetMapping("/pattern/list")
    public ResponseEntity<List<CollaborativePatternDTORespuesta>> getAllPatterns() {
        return ResponseEntity.ok(patternService.getAllPatterns());
    }

    // Buscar por id
    @GetMapping("/pattern/{id}")
    public ResponseEntity<CollaborativePatternDTORespuesta> getPatternById(@PathVariable Integer id) {
        CollaborativePatternDTORespuesta pattern = patternService.getPatternById(id);
        return ResponseEntity.ok(pattern);
    }

    // Crear pattern
    @PostMapping("/pattern/create")
    public ResponseEntity<CollaborativePatternDTORespuesta> createPattern(
            @RequestBody CollaborativePatternDTOPeticion request) {
        CollaborativePatternDTORespuesta newPattern = patternService.createPattern(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPattern);
    }

    // Actualizar pattern
    @PatchMapping("/pattern/update/{id}")
    public ResponseEntity<CollaborativePatternDTORespuesta> updatePattern(
            @PathVariable Integer id,
            @RequestBody CollaborativePatternDTOPeticion request) {
        CollaborativePatternDTORespuesta updatedPattern = patternService.updatePattern(id, request);
        return (updatedPattern != null) ? ResponseEntity.ok(updatedPattern) : ResponseEntity.notFound().build();
    }

    // Eliminar pattern
    @DeleteMapping("/pattern/delete/{id}")
    public ResponseEntity<Void> deletePattern(@PathVariable Integer id) {
        boolean deleted = patternService.deletePattern(id);
        return (deleted) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    ///////////////////////////////////////////////////////////////////
    /// Role
    ///////////////////////////////////////////////////////////////////
    @GetMapping("/role/list")
    public ResponseEntity<List<RoleDTORespuesta>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<RoleDTORespuesta> getRoleById(@PathVariable Integer id) {
        RoleDTORespuesta role = roleService.getRoleById(id);
        return ResponseEntity.ok(role);
    }

    @PostMapping("/role/create")
    public ResponseEntity<RoleDTORespuesta> createRole(
            @RequestBody RoleDTOPeticion request) {
        RoleDTORespuesta newRole = roleService.createRole(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRole);
    }

    @PatchMapping("/role/update/{id}")
    public ResponseEntity<RoleDTORespuesta> updateRole(
            @PathVariable Integer id,
            @RequestBody RoleDTOPeticion request) {
        RoleDTORespuesta updatedRole = roleService.updateRole(id, request);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/role/delete/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Integer id) {
        boolean deleted = roleService.deleteRole(id);
        return (deleted) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    /////////////////////////////////////////////////////////////
    // Asignacion entre childActivity y role
    /// /////////////////////////////////////////////////////////
    @PostMapping("/{idRole}/assign/{idActivity}")
    public ResponseEntity<RoleDTORespuesta> assignRoleToActivity(
            @PathVariable Integer idRole,
            @PathVariable Integer idActivity) {
        RoleDTORespuesta response = roleService.assignRoleToActivity(idRole, idActivity);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{idRole}/unassign/{idActivity}")
    public ResponseEntity<RoleDTORespuesta> unassignRole(
            @PathVariable Integer idRole,
            @PathVariable Integer idActivity) {

        RoleDTORespuesta respuesta = roleService.unassignRoleFromActivity(idRole, idActivity);
        return ResponseEntity.ok(respuesta);
    }

}