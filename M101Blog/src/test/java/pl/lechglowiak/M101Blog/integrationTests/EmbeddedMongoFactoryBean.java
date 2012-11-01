package pl.lechglowiak.M101Blog.integrationTests;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.mongodb.Mongo;
import com.mongodb.WriteConcern;

import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.mongo.tests.MongodForTestsFactory;

public class EmbeddedMongoFactoryBean implements FactoryBean<Mongo>, InitializingBean, DisposableBean {

    private MongodForTestsFactory factory;
    private Mongo mongo;

    @Override
    public void destroy() throws Exception {
        factory.shutdown();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        factory = MongodForTestsFactory.with(Version.Main.V2_2);
        mongo = factory.newMongo();
        mongo.setWriteConcern(WriteConcern.JOURNAL_SAFE);
    }

    @Override
    public Mongo getObject() throws Exception {
        return mongo;
    }

    @Override
    public Class<?> getObjectType() {
        return Mongo.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
