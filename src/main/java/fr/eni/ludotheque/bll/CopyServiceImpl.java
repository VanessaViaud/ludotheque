package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.dal.CopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CopyServiceImpl implements CopyService {

    private CopyRepository copyRepository;

    @Autowired
    public CopyServiceImpl(CopyRepository copyRepository) {
        this.copyRepository = copyRepository;
    }

    @Override
    public void returnCopy(String Id) {
        copyRepository.findById(Id).ifPresent(copy -> {
            copy.setAvailable(true);
            copyRepository.save(copy);
        })
        ;
    }


}
