package fr.slem.rest;

import fr.slem.dto.MobileSuitPostDto;
import fr.slem.model.MobileSuit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author slemoine
 */
@RequestMapping(MobileSuitFactoryController.API_ROOT_RESOURCE)
@RestController
public class MobileSuitFactoryController {

    public static final String API_ROOT_RESOURCE = "/mobilesuits";

    public static final String SEARCH_RESOURCE = "/search";

    public static final String PARAM_ID = "idmobilesuit";

    public static final String PARAM_MODEL_NAME = "modelname";

    private List<MobileSuit> mobileSuits;

    public MobileSuitFactoryController(){
        final MobileSuit mobileSuit1 = new MobileSuit();
        mobileSuit1.setId(1L);
        mobileSuit1.setModelName("RX-78");
        mobileSuit1.setWeapons(Arrays.asList("Saber", "Rifle"));

        final MobileSuit mobileSuit2 = new MobileSuit();
        mobileSuit2.setId(2L);
        mobileSuit2.setModelName("MS-06 Zaku II");
        mobileSuit2.setWeapons(Arrays.asList("Cannon"));

        mobileSuits = new ArrayList<>();
        mobileSuits.add(mobileSuit1);
        mobileSuits.add(mobileSuit2);
    }

    /**
     * Get all existing mobile suits
     * @return List of MobileSuit
     */
    @GetMapping
    public List<MobileSuit> getAll(){
        return mobileSuits;
    }


    /**
     * Search Mobile suit for which model name starts with...
     *
     * @param modelName model name
     * @return List of MobileSuit
     */
    @GetMapping(SEARCH_RESOURCE)
    public List<MobileSuit> searchByModelName(@RequestParam(PARAM_MODEL_NAME) String modelName){
        return mobileSuits.parallelStream()
                .filter(m -> m.getModelName().toUpperCase().startsWith(modelName.toUpperCase()))
                .collect(Collectors.toList());
    }


    /**
     * Get Mobile Suit by id
     *
     * @param idMobileSuit mobile suit identifier
     * @return MobileSuit
     */
    @GetMapping("/{" + PARAM_ID + "}")
    public MobileSuit getById(@PathVariable(PARAM_ID) Long idMobileSuit){
        return mobileSuits.parallelStream()
                .filter(m -> m.getId().equals(idMobileSuit))
                .findFirst()
                .orElse(null);
    }


    /**
     * create Mobile Suit
     *
     * @param mobileSuitPostDto mobile suit info
     */
    @PostMapping
    public void create(@RequestBody MobileSuitPostDto mobileSuitPostDto) {

        //yeaaah, it's not thread safe
        Long maxId = this.mobileSuits.stream()
                .max(Comparator.comparingLong(MobileSuit::getId))
                .map(MobileSuit::getId)
                .orElse(0L);

        MobileSuit mobileSuit = new MobileSuit();
        mobileSuit.setId(maxId + 1);
        mobileSuit.setModelName(mobileSuitPostDto.getModelName());
        mobileSuit.setWeapons(mobileSuitPostDto.getWeapons());
        this.mobileSuits.add(mobileSuit);
    }

}