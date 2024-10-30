package ar.unrn.tp.cache;

import ar.unrn.tp.jpa.services.SaleService;
import ar.unrn.tp.modelo.Sale;
import redis.clients.jedis.Jedis;

import java.util.List;

public class CacheService {
    private final Jedis jedis;
    private final String CACHE_KEY = "ultimas_compras";
    private final int CACHE_SIZE = 3;
    private final int CACHE_EXPIRATION_TIME = 180; // en segundos
    private SaleService ss;

    public CacheService(SaleService s) {
        // Conectar a Redis
        this.jedis = new Jedis("localhost", 6379);
        this.ss = s;
    }

    public List<String> getUltimasCompras() {
        // Verificar si las compras están en cache
        List<String> comprasCacheadas = jedis.lrange(CACHE_KEY, 0, -1);

        if (comprasCacheadas != null && !comprasCacheadas.isEmpty()) {
            return comprasCacheadas;
        }


        List<Sale> ultimasCompras = getSales();

            // Guardarlas en cache
        cargarEnCache(ultimasCompras);

        return jedis.lrange(CACHE_KEY, 0, -1);
    }

    private void cargarEnCache(List<Sale> compras) {
        // Limpiar cache anterior
        jedis.del(CACHE_KEY);
        int cont = 0;
        // Guardar las compras en cache
        for (Sale sa : compras) {
            if(cont<CACHE_SIZE) {
                jedis.rpush(CACHE_KEY, sa.toString());
                cont++;
            }

        }

        // Establecer tiempo de expiración
        jedis.expire(CACHE_KEY, CACHE_EXPIRATION_TIME);
    }

    private List<Sale> getSales() {
        return ss.ventas();
    }

    public void close() {
        jedis.close();
    }
}
