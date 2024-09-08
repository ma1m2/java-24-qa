package hbm;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addressbook")
public class ContactRecord {
  @Id
  public int id;
  public String firstname;
  public String lastname;
  public String address;
  public String address2;
  public String home;
  public String mobile;
  public String work;
  public String phone2;
  public String email;
  public String email2;
  public String email3;

/*  @ManyToMany(fetch = EAGER)
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"),
          inverseJoinColumns = @JoinColumn(name = "group_id"))
  public List<GroupRecord> groups;*/

  public ContactRecord() {}
  public ContactRecord(int id, String address, String firstname, String lastname) {
    this.id = id;
    this.address = address;
    this.firstname = firstname;
    this.lastname = lastname;
  }

  public ContactRecord(int id, String firstname, String lastname, String address, String address2,
                       String home, String mobile, String work, String phone2,
                       String email, String email2, String email3) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.address2 = address2;
    this.home = home;
    this.mobile = mobile;
    this.work = work;
    this.phone2 = phone2;
    this.email = email;
    this.email2 = email2;
    this.email3 = email3;
  }
}
