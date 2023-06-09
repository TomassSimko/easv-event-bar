package com.event_bar_easv.dal.reporitory;

import com.event_bar_easv.be.Event;
import com.event_bar_easv.be.SpecialTicketType;
import com.event_bar_easv.be.TicketType;
import com.event_bar_easv.be.user.AppUser;
import com.event_bar_easv.dal.interfaces.IEventRepository;
import com.event_bar_easv.dal.mappers.EventMapper;
import com.event_bar_easv.dal.mappers.UserMapper;
import com.event_bar_easv.dal.myBatis.MyBatisConnectionFactory;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class EventRepository implements IEventRepository {
    static Logger logger = LoggerFactory.getLogger(UserRepository.class);


    @Override
    public List<Event> getAllEvents() {
        List<Event> fetchedEvents = new ArrayList<>();
        try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession()) {
            EventMapper mapper = session.getMapper(EventMapper.class);
            fetchedEvents = mapper.getAllEvents();
        } catch (Exception ex) {
            logger.error("An error occurred mapping tables", ex);
        }
        return fetchedEvents;
    }


    @Override
    public List<SpecialTicketType> getAllSpecialTickets() {
        List<SpecialTicketType> fetchedEvents = new ArrayList<>();
        try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession()) {
            EventMapper mapper = session.getMapper(EventMapper.class);
            fetchedEvents = mapper.getAllSpecialTickets();
        } catch (Exception ex) {
            logger.error("An error occurred mapping tables", ex);
        }
        return fetchedEvents;
    }

    @Override
    public int createEvent(int id, String title, String loc, Date startDate2, Date endDate2, String startTime2, String endTime2, String notes2) {
        int returnedId = 0;
        try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession()) {
            EventMapper mapper = session.getMapper(EventMapper.class);
            int affectedRows = mapper.createEvent(
                    id,
                    title,
                    loc,
                    startDate2,
                    endDate2,
                    startTime2,
                    endTime2,
                    notes2
            );
            session.commit();
            returnedId = affectedRows > 0 ? id : 0;
        } catch (Exception ex) {
            logger.error("An error occurred mapping tables", ex);
        }
        return returnedId;
    }

    @Override
    public int addTypeToEvent(int id, TicketType ticketType) {
        int finalAffectedRows = 0;
        try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession()) {
            EventMapper mapper = session.getMapper(EventMapper.class);
            int affectedRows = mapper.addTypeToTicket(id,ticketType.getId());
            session.commit();
            return affectedRows;
        } catch (Exception ex) {
            logger.error("An error occurred mapping tables", ex);
        }
        return finalAffectedRows;
    }

    @Override
    public int createTicketType(TicketType ticketType) {
        int returnedId = 0;
        try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession()) {
            EventMapper mapper = session.getMapper(EventMapper.class);
            int affectedRows = mapper.createTypeForTicket(ticketType);
            session.commit();
            returnedId = affectedRows > 0 ? ticketType.getId() : 0;
        } catch (Exception ex) {
            logger.error("An error occurred mapping tables", ex);
        }
        return returnedId;
    }

    @Override
    public int addSpecialTicketToEvent(SpecialTicketType specialTicketType, int eventId) {
        int finalAffectedRows = 0;
        try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession()) {
            EventMapper mapper = session.getMapper(EventMapper.class);
            int affectedRows = mapper.addSpecialTicketToEvent(eventId,specialTicketType.getId());
            session.commit();
            return affectedRows;
        } catch (Exception ex) {
            logger.error("An error occurred mapping tables", ex);
        }
        return finalAffectedRows;
    }

    @Override
    public int createSpecialTicket(SpecialTicketType specialTicketType) {
        int returnedId = 0;
        try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession()) {
            EventMapper mapper = session.getMapper(EventMapper.class);
            int affectedRows = mapper.createSpecialTicketType(specialTicketType);
            session.commit();
            returnedId = affectedRows > 0 ? specialTicketType.getId() : 0;
        } catch (Exception ex) {
            logger.error("An error occurred mapping tables", ex);
        }
        return returnedId;
    }

}
