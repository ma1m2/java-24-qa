package hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Table(name = "group_list")
public class GroupRecord {
  @Id
  @Column(name="group_id")
  public int id;
  @Column(name="group_name")
  public String name;
  @Column(name="group_header")
  public String header;
  @Column(name="group_footer")
  public String footer;
  public Date deprecated = new Date();

  @ManyToMany(fetch = EAGER)
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn(name = "group_id"),
          inverseJoinColumns = @JoinColumn(name = "id"))
  public List<ContactRecord> contacts;

  public GroupRecord() {
  }

  public GroupRecord(int id, String name, String header, String footer) {
    this.id = id;
    this.name = name;
    this.header = header;
    this.footer = footer;
  }

}
