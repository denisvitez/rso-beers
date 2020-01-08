package si.fri.rso.sn.beers.services.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.rso.sn.beers.models.entities.Beer;
import si.fri.rso.sn.beers.services.configuration.AppProperties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;


@RequestScoped
public class BeersBean {

    private Logger log = Logger.getLogger(BeersBean.class.getName());

    @Inject
    private EntityManager em;

    @Inject
    private AppProperties appProperties;

    @Inject
    private BeersBean beersBean;

    private Client httpClient;

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
//        baseUrlFriends = "http://localhost:8081"; // only for demonstration
    }


    public List<Beer> getBeers() {
        TypedQuery<Beer> query = em.createNamedQuery("Beer.getAll", Beer.class);
        System.out.println(query.toString());
        return query.getResultList();
    }

    public Beer getBeer(Integer beerId) {

        Beer item = em.find(Beer.class, beerId);

        if (item == null) {
            throw new NotFoundException();
        }
        return item;
    }

    public Beer createBeer(Beer beer) {
        try {
            beginTx();
            em.persist(beer);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }
        return beer;
    }

    public Beer putScore(Integer beerId, Beer beer) {
        Beer b = em.find(Beer.class, beerId);
        if (b == null) {
            return null;
        }
        try {
            beginTx();
            beer.setId(b.getId());
            beer = em.merge(beer);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }
        return beer;
    }

    public boolean deleteBeer(Integer beerId) {

        Beer b = em.find(Beer.class, beerId);

        if (b != null) {
            try {
                beginTx();
                em.remove(b);
                commitTx();
            } catch (Exception e) {
                rollbackTx();
            }
        } else
            return false;

        return true;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive())
            em.getTransaction().begin();
    }

    private void commitTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().commit();
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().rollback();
    }
}
