package pro.sky.telegrambot.service.rule;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.sky.telegrambot.dto.DynamicRuleDto;
import pro.sky.telegrambot.model.secondary.QueryConditionEntity;
import pro.sky.telegrambot.model.secondary.RuleEntity;
import pro.sky.telegrambot.repository.secondary.DynamicRuleRepository;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DynamicRuleService {

    private final Logger logger = LoggerFactory.getLogger(DynamicRuleService.class);

    private final DynamicRuleRepository ruleRepository;

    @Transactional(readOnly = true)
    public List<DynamicRuleDto> getAllRules() {
        return ruleRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public void deleteRule(UUID productId) {
        ruleRepository.deleteByProductId(productId);
    }

    @Transactional(readOnly = true)
    public DynamicRuleDto getRuleById(UUID id) {

        logger.info("find rule by id {}", id);
        logger.error("rule not found");

        return ruleRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("Правило не найдено с идентификатором: " + id));
    }

    @Transactional(readOnly = true)
    public DynamicRuleDto getRuleByProductId(UUID productId) {

        logger.info("find rule by product id {}", productId);
        logger.error("rule not found");

        return ruleRepository.findByProductId(productId)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("правило не найдено с productId: " + productId));
    }

    public DynamicRuleDto updateRule(UUID id, DynamicRuleDto dto) {

        logger.info("find rule by id {}", id);
        logger.error("rule not found");

        RuleEntity existing = ruleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Правило не найдено с идентификатором: " + id));

        // Вручную обновляем поля
        existing.setProductName(dto.getProductName());
        existing.setProductId(dto.getProductId());
        existing.setProductText(dto.getProductText());

        if (dto.getRule() != null) {
            existing.getRule().clear();
            existing.getRule().addAll(dto.getRule().stream()
                    .map(this::mapToQueryEntity)
                    .collect(Collectors.toList()));
        }

        logger.info("rule updated");

        return mapToDto(ruleRepository.save(existing));
    }

    @Transactional(readOnly = true)
    public boolean existsByProductId(UUID productId) {
        logger.info("exists by product id {}", productId);
        logger.error("rule not found");

        return ruleRepository.existsByProductId(productId);
    }

    public DynamicRuleDto createRule(@Valid DynamicRuleDto request) {

        logger.info("create rule");
        logger.error("rule not found");

        RuleEntity entity = mapToEntity(request);
        entity.setId(null);

        RuleEntity savedEntity = ruleRepository.save(entity);
        return mapToDto(savedEntity);
    }


    private DynamicRuleDto mapToDto(RuleEntity entity) {

        logger.info("map to dto");
        logger.error("not implemented");
        if (entity == null) return null;

        DynamicRuleDto dto = new DynamicRuleDto();
        dto.setId(entity.getId());
        dto.setProductName(entity.getProductName());
        dto.setProductId(entity.getProductId());
        dto.setProductText(entity.getProductText());

        if (entity.getRule() != null) {
            dto.setRule(entity.getRule().stream()
                    .map(this::mapToQueryDto)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    private RuleEntity mapToEntity(DynamicRuleDto dto) {

        logger.info("map to entity");
        logger.error("not implemented");

        if (dto == null) return null;

        RuleEntity entity = new RuleEntity();
        entity.setId(dto.getId());
        entity.setProductName(dto.getProductName());
        entity.setProductId(dto.getProductId());
        entity.setProductText(dto.getProductText());

        if (dto.getRule() != null) {
            entity.setRule(dto.getRule().stream()
                    .map(this::mapToQueryEntity)
                    .collect(Collectors.toList()));
        }

        return entity;
    }

    private QueryConditionEntity mapToQueryEntity(DynamicRuleDto.QueryConditionDto qDto) {

        logger.info("map to query entity");
        logger.error("not implemented");

        QueryConditionEntity qEntity = new QueryConditionEntity();
        qEntity.setQuery(qDto.getQuery());
        qEntity.setArguments(qDto.getArguments());
        qEntity.setNegate(qDto.isNegate());
        return qEntity;
    }

    private DynamicRuleDto.QueryConditionDto mapToQueryDto(QueryConditionEntity qEntity) {

        logger.info("map to query entity");
        logger.error("not implemented");

        DynamicRuleDto.QueryConditionDto qDto = new DynamicRuleDto.QueryConditionDto();
        qDto.setQuery(qEntity.getQuery());
        qDto.setArguments(qEntity.getArguments());
        qDto.setNegate(qEntity.isNegate());
        return qDto;
    }
}