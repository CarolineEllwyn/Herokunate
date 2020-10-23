package caroline.hibernationapp.hibernation.Controllers;

import caroline.hibernationapp.hibernation.Models.Actor;
import caroline.hibernationapp.hibernation.Repositories.ActorRepository;
import caroline.hibernationapp.hibernation.Utils.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import caroline.hibernationapp.hibernation.Models.CommonResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class ActorController {

    @Autowired
    private ActorRepository repository;


    @GetMapping("/")
    public ResponseEntity<CommonResponse> actorRoot(HttpServletRequest request) {
        Command command = new Command(request);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.data = null;
        commonResponse.message = "Not implemented";

        HttpStatus response = HttpStatus.NOT_IMPLEMENTED;

        command.setResult(response);
        return new ResponseEntity<>(commonResponse, response);
    }


    @PostMapping("/actor/")
    public ResponseEntity<CommonResponse> createNewActor(HttpServletRequest request, HttpServletResponse resp, @RequestBody Actor actor) {
        Command command = new Command(request);

        actor = repository.save(actor);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.data = actor;
        commonResponse.message = "Actor created";

        HttpStatus response = HttpStatus.CREATED;

        command.setResult(response);
        return new ResponseEntity<>(commonResponse, response);
    }


    @GetMapping("/actor/all")
    public ResponseEntity<CommonResponse> listAllActors(HttpServletRequest request) {
        Command command = new Command(request);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.data = repository.findAll();
        commonResponse.message = "All actors";
        HttpStatus response = HttpStatus.OK;

        command.setResult(response);

        return new ResponseEntity<>(commonResponse, response);
    }


    @GetMapping("/actor/{id}")
    public ResponseEntity<CommonResponse> getActorById(HttpServletRequest request, @PathVariable("id") Integer id) {
        Command command = new Command(request);

        CommonResponse commonResponse = new CommonResponse();
        HttpStatus response;

        if (repository.existsById(id)) {
            commonResponse.data = repository.findById(id);
            commonResponse.message = "Actor with id: " + id;
            response = HttpStatus.OK;
        } else {
            commonResponse.data = null;
            commonResponse.message = "Actor not found with id:" + id;
            response = HttpStatus.NOT_FOUND;
        }
        command.setResult(response);

        return new ResponseEntity<>(commonResponse, response);
    }


    @DeleteMapping("actor/{id}")
    public ResponseEntity<CommonResponse> deleteActor(HttpServletRequest request, @PathVariable Integer id){
        Command command = new Command(request);

        CommonResponse commonResponse = new CommonResponse();
        HttpStatus resp;

        if(repository.existsById(id)) {
            repository.deleteById(id);
            commonResponse.message = "Actor with id: " + id + " has ben deleted";
            resp = HttpStatus.OK;
        } else {
            commonResponse.message = "Actor with id: " + id + " was not found";
            resp = HttpStatus.NOT_FOUND;
        }

        command.setResult(resp);
        return new ResponseEntity<>(commonResponse, resp);
    }

}