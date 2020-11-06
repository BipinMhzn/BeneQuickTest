package bqtest.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MemberImporterImpl implements MemberImporter {


    public Map<String, List<Member>> processFile(File inputMemberFile) throws IOException {

        List<Member> membersFromFile = importMembers(inputMemberFile);

        return splitMembersByState(membersFromFile);

    }


    private List<Member> importMembers(File inputFile) throws IOException {
        return Files.lines(inputFile.toPath())
                .map(line -> {
                   //TODO implement here
                    return new Member();
                }).collect(Collectors.toList());
    }


    private Map<String, List<Member>> splitMembersByState(List<Member> validMembers) {
       //TODO implement this
        return null;
    }
}
