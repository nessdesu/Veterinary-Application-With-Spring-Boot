package com.example.nesdeneme;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Collection;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired
    private IUserRepository repo;

    @Autowired
    private IAppointmentRepository appointmentRepository;

    @Autowired
    private IDoctorRepository doctorRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ICallDoctorRepository callDoctorRepository;

    @Autowired
    private IQuestionRepository questionRepository;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("xxx@eru.com");
        user.setPassword("xxx");
        user.setFirstName("kedi");
        user.setLastName("xxx");
        user.setPhoneNumber("123456789");
        user.setEnabled(true);

        User savedUser = repo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void testFindUserByEmail() {
        String email = "nespet@eru.com";

        User user = repo.findByEmail(email);

        assertThat(user).isNotNull();

    }

    @Test
    public void testCreateAppointment() {
        Appointment appointment = new Appointment();
        appointment.setPettype("kedi");
        appointment.setDoctorname("dr xxx");
        appointment.setAppointdate("10.06.2021");
        appointment.setAppointstarthour("15:00");
        appointment.setAppointendhour("16:00");
        appointment.setPatientname("xxx xx");
        appointment.setEmail("xxx@eru.com");
        appointment.setPhonenumber("1234567898");

        Doctors doctors = new Doctors();
        ;
        Appointment savedAppoint = appointmentRepository.save(appointment);

        Appointment exitAppoint = entityManager.find(Appointment.class, savedAppoint.getId());

        assertThat(exitAppoint.getId()).isEqualTo(appointment.getId());

    }

    @Test
    public void testFindAppointmentByEmail() {
        String email = "nespet@eru.com";

        User user = appointmentRepository.findByEmail(email);

        assertThat(user).isNotNull();

    }

    @Test
    public void testCreateDoctor() {
        Doctors doctors = new Doctors();
        doctors.setVetname("xxx xxxx");
        doctors.setExtensionNumber("38111");
        doctors.getVetname();

        Doctors savedDoctor = doctorRepository.save(doctors);

        Doctors exitDoctor = entityManager.find(Doctors.class, savedDoctor.getId());

        assertThat(exitDoctor.getExtensionNumber()).isEqualTo(doctors.getExtensionNumber());
    }

    @Test
    public void testFindByExtensionNumber() {
        String id = "31112";
        Doctors doctors = doctorRepository.findById(id);

        assertThat(doctors).isNotNull();
    }

    @Test
    public void testCreateRole() {
        Role role = new Role();
        role.setName("ADMIN");

        Role savedRole = roleRepository.save(role);
        Role exitRole = entityManager.find(Role.class, savedRole.getId());

        assertThat(exitRole.getName()).isEqualTo(role.getName());
    }

    @Test
    void testFindByName() {
        String name = "xxx";
        Role role = roleRepository.findByName(name);

        assertThat(role).isNotNull();
    }

    @Test
    public void testCreateCallDoctor() {
        CallDoctor callDoctor = new CallDoctor();
        callDoctor.setAddress("xxx mah. erciyes üni kampüsü, mühendislik fakültesi");
        callDoctor.setDate("12.06.2020");
        callDoctor.setType("xxx");
        callDoctor.setHour("15:00");
        callDoctor.setVetName("xxx xxx");

        CallDoctor savedCall = callDoctorRepository.save(callDoctor);
        CallDoctor exitCall = entityManager.find(CallDoctor.class, savedCall.getId());

        assertThat(exitCall.getDate()).isEqualTo(callDoctor.getDate());
    }
    @Test
    void testFindByCallHour(){
        String hour = "14:00";
        CallDoctor callDoctor = callDoctorRepository.findByHour(hour);

        assertThat(callDoctor).isNotNull();
    }

    @Test
    public void testCreateQuestions() {
        Questions questions = new Questions();
        questions.setQuestion("Kedim ve köpeğim birbirlerinin mamalarını yiyor. Zararı var mı? Nasıl önelerim?");
        questions.setAnswer("Genç hayvanlar ve protein alerjisi olmayan köpekler için çok fazla önemi olmamakla birlikte, özellikle belirli yaşın üzerindeki hayvanlarda kalp ve böbrek gibi hayati organ hastalıkları bulunanlarda zararlı olabilir. Aşırı tüketimlerinde protein alerjisine yol açıp deri hastalıklarına zemin hazırlayabilir. Bu gibi durumlarda kedi mamalarının köpek tarafından yenmesi, bir şekilde engellenmelidir. Köpekler öğünle beslendikleri için mamanın, sizin kontrolünüzde verilmesi en doğru çözümdür.");

        Questions savedQuestion = questionRepository.save(questions);
        Questions exitQuestion = entityManager.find(Questions.class, savedQuestion.getQuestion());

        assertThat(exitQuestion.getQuestion()).isEqualTo(questions.getQuestion());
    }


}
