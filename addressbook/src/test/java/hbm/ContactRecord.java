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
}
