package org.larina.application.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ControllerUtils {
    static Map<String, String> getErrors(BindingResult bindingResult){
        //получаем лист с ошибками и преобразуем его в Map
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                        //ключ  (имя ошибки + Error = например, textError)
                        fieldError -> fieldError.getField() + "Error",
                        //значения
                        FieldError::getDefaultMessage
                );
        //полученный map возвращаем
       return bindingResult.getFieldErrors().stream().collect(collector);
    }
}

/*
В классе Stream есть метод collect(),
он используется для того, чтобы перейти от потоков к привычным коллекциям
— List<T>, Set<T>, Map<T, R> и другим.

В метод collect() нужно передать специальный объект — collector.
Этот объект вычитывает все данные из потока, преобразует их к определенной коллекции
и возвращает ее. А следом за ним эту же коллекцию возвращает и сам метод collect.

public interface Collector<T,A,R>

Параметры типа:
T - тип входных элементов для операции приведения
A - изменяемый тип накопления операции приведения (часто скрытый как деталь реализации)
R - тип результата операции приведения (List<T>, Set<T>, Map<T, R>)

Collector определяется четырьмя функциями, которые работают вместе,
чтобы накапливать записи в изменяемый контейнер результатов и,
при необходимости, выполнять окончательное преобразование результата.
Они есть:

1. создание нового контейнера результатов (supplier())
2. включение нового элемента данных в контейнер результатов (accumulator())
3. объединение двух контейнеров результатов в один (combiner())
4. выполнение необязательного окончательного преобразования в контейнере (finisher())

static <T,A,R> Collector<T,A,R>
Возвращает новый Collector,
описанный заданными функциями: supplier, accumulator, combiner, и finisher.

of(Supplier<A> supplier - функция, которая создает и возвращает новый изменяемый контейнер результатов,
 BiConsumer<A,T> accumulator - функция, которая складывает значение в изменяемый контейнер результатов,
  BinaryOperator<A> combiner - функция, которая принимает два частичных результата и объединяет их.
  Функция объединения может преобразовывать состояние из одного аргумента в другой и возвращать его
   или может возвращать новый контейнер результатов,
   Function<A,R> finisher - окончательное преобразование из промежуточного типа накопления A в конечный тип результата R,
    Collector.Characteristics... characteristics - неизменяемый набор характеристик коллектора)

 Знак вопроса (?) в среднем параметре говорит о том, что внутренний тип реализации для публичного API не важен.
 */