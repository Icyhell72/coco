package com.example.pidevcocomarket.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "utilisateur")
@Getter
@Setter
@NoArgsConstructor /*constructeur vide*/
@AllArgsConstructor /*constructeur avec tous les attributs*/
@ToString
@Builder
public class User implements java.io.Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String company;
    private String address;
    @Enumerated(EnumType.STRING)
    private RoleType role;
    //@ManyToMany(cascade = CascadeType.ALL)
    //@ToString.Exclude
    //private Set<Role> roles;
    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] logo;
    private Integer fidelity;
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private Set<ChatBox> chatBoxes;
    @OneToMany(cascade = CascadeType.ALL,mappedBy="vendor")
    @JsonIgnore
    @ToString.Exclude
    private Set<Contract> contrats;
    @OneToMany(cascade = CascadeType.ALL,mappedBy="user")
    @JsonIgnore
    @ToString.Exclude
    private Set<Boutique> boutiques;

    //SPRING SECURITY//
    @Override
    /*public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleType().toString()))
                .collect(Collectors.toList());
    }*/
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        list.add(new SimpleGrantedAuthority(role.name()));
        return list;
    }

    @Override
    public String getUsername() {return email;}
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public boolean isAccountNonExpired() {return true;}
    @Override
    public boolean isAccountNonLocked() {return true;}
    @Override
    public boolean isCredentialsNonExpired() {return true;}
    @Override
    public boolean isEnabled() {return true;}
}
