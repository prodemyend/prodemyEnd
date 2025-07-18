package za.ac.cput.service;

import java.io.IOException;

public interface IService <T, ID>{
    T create (T t);
    T read (ID id);
    T update(T t);
}
