package bqtest.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
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
                    int length = line.length();
                    Member member = new Member();
                    member.setId(line.substring(0, 12).trim());
                    member.setLastName(line.substring(12, 37).trim());
                    member.setFirstName(line.substring(37, 62).trim());
                    member.setAddress(line.substring(62, 92).trim());
                    member.setCity(line.substring(92, 112).trim());
                    member.setState(line.substring(112, 116).trim());
                    member.setZip(line.substring(116, length).trim());

                    return member;
                }).collect(Collectors.toList());
    }


    private Map<String, List<Member>> splitMembersByState(List<Member> validMembers) {
        HashSet<Member> uniqueMembers = new HashSet<>(validMembers);

        return uniqueMembers.stream()
                .collect(Collectors.groupingBy(Member::getState));
    }
}
