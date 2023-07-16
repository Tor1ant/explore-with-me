package com.github.explore_with_me.main.event.model;

import com.github.explore_with_me.main.category.model.Category;
import com.github.explore_with_me.main.event.enums.State;
import com.github.explore_with_me.main.user.model.User;
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "annotation")
    private String annotation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_events", joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> confirmedRequests;
    @Column(name = "created_on")
    private LocalDateTime createdOn;
    @Column(name = "description")
    private String description;
    @Column(name = "eventDate")
    private LocalDateTime eventDate;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "initiator_id")
    private User initiator;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    //example: true
//Нужно ли оплачивать участие
    @Column(name = "paid")
    private boolean paid;

    //значение 0 означает отсутствие ограничения
    @Column(name = "participantLimit")
    private int participantLimit;

    //Дата и время публикации события (в формате "yyyy-MM-dd HH:mm:ss")
    @Column(name = "published_on")
    private LocalDateTime publishedOn;

    //default: true
    //Нужна ли пре-модерация заявок на участие
    @Column(name = "requestModeration")
    boolean requestModeration;

    //example: PUBLISHED
    //Список состояний жизненного цикла события
    @Column(name = "state")
    private State state;

    //example: Знаменитое шоу 'Летающая кукуруза'
    //Заголовок
    @Column(name = "title")
    private String title;

    //example: 999
    //Количество просмотрев события

    private int views;
}
