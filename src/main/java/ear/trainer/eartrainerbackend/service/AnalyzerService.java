package ear.trainer.eartrainerbackend.service;

import ear.trainer.eartrainerbackend.client.AnalyzerClient;
import ear.trainer.eartrainerbackend.dto.AnalyzerResponseDto;
import ear.trainer.eartrainerbackend.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalyzerService {
    @Autowired
    private AnalyzerClient analyzerClient;

    @Autowired
    private GameSessionService gameSessionService;

    public AnalyzerResponseDto analyze(UserDto dto) {


        AnalyzerResponseDto resDto = analyzerClient.analyze(gameSessionService.getScorePerNote(dto));

//        analyzerDto.setText();
        return resDto;
    }

}
